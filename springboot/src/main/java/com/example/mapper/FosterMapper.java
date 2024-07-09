package com.example.mapper;

import com.example.entity.Foster;

import java.util.List;

/**
 * 操作foster相关数据接口
*/
public interface FosterMapper {

    /**
      * 新增
    */
    int insert(Foster foster);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Foster foster);

    /**
      * 根据ID查询
    */
    Foster selectById(Integer id);

    /**
      * 查询所有
    */
    List<Foster> selectAll(Foster foster);

}