package com.example.mapper;

import com.example.entity.Submit;

import java.util.List;

/**
 * 操作submit相关数据接口
*/
public interface SubmitMapper {

    /**
      * 新增
    */
    int insert(Submit submit);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Submit submit);

    /**
      * 根据ID查询
    */
    Submit selectById(Integer id);

    /**
      * 查询所有
    */
    List<Submit> selectAll(Submit submit);

}