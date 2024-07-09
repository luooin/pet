package com.example.mapper;

import com.example.entity.Adopt;
import com.example.entity.Animal;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作adopt相关数据接口
*/
public interface AdoptMapper {

    /**
      * 新增
    */
    int insert(Adopt adopt);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(Adopt adopt);

    /**
      * 根据ID查询
    */
    Adopt selectById(Integer id);

    /**
      * 查询所有
    */
    List<Adopt> selectAll(Adopt adopt);

    @Select("select * from adopt group by animal_id")
    List<Adopt> selectChange();
}