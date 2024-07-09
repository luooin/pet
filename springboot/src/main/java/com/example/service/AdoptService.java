package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.AdoptStatusEnum;
import com.example.common.enums.AnimalStatusEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.Adopt;
import com.example.entity.Animal;
import com.example.mapper.AdoptMapper;
import com.example.mapper.AnimalMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 领养记录表业务处理
 **/
@Service
public class AdoptService {

    @Resource
    private AdoptMapper adoptMapper;
    @Resource
    private AnimalMapper animalMapper;

    /**
     * 新增
     */
    public void add(Adopt adopt) {
        adopt.setTime(DateUtil.now());
        adopt.setStatus(AdoptStatusEnum.ADOPT_YES.status);
        adoptMapper.insert(adopt);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        adoptMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            adoptMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Adopt adopt) {
        if (AnimalStatusEnum.ADOPT_CANCEL.status.equals(adopt.getStatus())) {
            // 已归还我们需要把宠物信息的状态同步成 待领养
            Animal animal = animalMapper.selectById(adopt.getAnimalId());
            animal.setStatus(AnimalStatusEnum.ADOPT_NO.status);
            animalMapper.updateById(animal);
        }
        adoptMapper.updateById(adopt);
    }

    /**
     * 根据ID查询
     */
    public Adopt selectById(Integer id) {
        return adoptMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Adopt> selectAll(Adopt adopt) {
        return adoptMapper.selectAll(adopt);
    }

    /**
     * 分页查询
     */
    public PageInfo<Adopt> selectPage(Adopt adopt, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if (RoleEnum.USER.name().equals(currentUser.getRole())) {
            adopt.setUserId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Adopt> list = adoptMapper.selectAll(adopt);
        return PageInfo.of(list);
    }

    public List<Animal> selectChange() {
        List<Adopt> adopts = adoptMapper.selectChange();
        List<Animal> list = new ArrayList<>();
        for (Adopt adopt : adopts) {
            Animal animal = animalMapper.selectById(adopt.getAnimalId());
            if (ObjectUtil.isNotEmpty(animal)) {
                list.add(animal);
            }
        }
        return list;
    }
}