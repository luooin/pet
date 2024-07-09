package com.example.mapper;

import com.example.entity.Animal;

import java.util.List;

/**
 * 操作animal相关数据接口
*/
public interface AnimalMapper {

    /**
      * 新增
    */
    int insert(Animal animal);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Animal animal);

    /**
      * 根据ID查询
    */
    Animal selectById(Integer id);

    /**
      * 查询所有
    */
    List<Animal> selectAll(Animal animal);

}