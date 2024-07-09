package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Animal;
import com.example.mapper.AnimalMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告信息表业务处理
 **/
@Service
public class AnimalService {

    @Resource
    private AnimalMapper animalMapper;

    /**
     * 新增
     */
    public void add(Animal animal) {
        animalMapper.insert(animal);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        animalMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            animalMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Animal animal) {
        animalMapper.updateById(animal);
    }

    /**
     * 根据ID查询
     */
    public Animal selectById(Integer id) {
        return animalMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Animal> selectAll(Animal animal) {
        return animalMapper.selectAll(animal);
    }

    /**
     * 分页查询
     */
    public PageInfo<Animal> selectPage(Animal animal, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Animal> list = animalMapper.selectAll(animal);
        return PageInfo.of(list);
    }

}