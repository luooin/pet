package com.example.service;

import cn.hutool.core.date.DateUtil;
import com.example.entity.Account;
import com.example.entity.Submit;
import com.example.mapper.SubmitMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 上报信息表业务处理
 **/
@Service
public class SubmitService {

    @Resource
    private SubmitMapper submitMapper;

    /**
     * 新增
     */
    public void add(Submit submit) {
        submit.setTime(DateUtil.now());
        submitMapper.insert(submit);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        submitMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            submitMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Submit submit) {
        submitMapper.updateById(submit);
    }

    /**
     * 根据ID查询
     */
    public Submit selectById(Integer id) {
        return submitMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<Submit> selectAll(Submit submit) {
        return submitMapper.selectAll(submit);
    }

    /**
     * 分页查询
     */
    public PageInfo<Submit> selectPage(Submit submit, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Submit> list = submitMapper.selectAll(submit);
        return PageInfo.of(list);
    }

}