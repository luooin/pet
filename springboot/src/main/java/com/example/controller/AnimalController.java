package com.example.controller;

import com.example.common.Result;
import com.example.entity.Animal;
import com.example.service.AnimalService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 宠物信息表前端操作接口
 **/
@RestController
@RequestMapping("/animal")
public class AnimalController {

    @Resource
    private AnimalService animalService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Animal animal) {
        animalService.add(animal);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        animalService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        animalService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Animal animal) {
        animalService.updateById(animal);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Animal animal = animalService.selectById(id);
        return Result.success(animal);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Animal animal ) {
        List<Animal> list = animalService.selectAll(animal);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Animal animal,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Animal> page = animalService.selectPage(animal, pageNum, pageSize);
        return Result.success(page);
    }

}