package com.example.controller;

import com.example.common.Result;
import com.example.entity.Adopt;
import com.example.entity.Animal;
import com.example.service.AdoptService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 领养记录表前端操作接口
 **/
@RestController
@RequestMapping("/adopt")
public class AdoptController {

    @Resource
    private AdoptService adoptService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Adopt adopt) {
        adoptService.add(adopt);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        adoptService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        adoptService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Adopt adopt) {
        adoptService.updateById(adopt);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Adopt adopt = adoptService.selectById(id);
        return Result.success(adopt);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Adopt adopt ) {
        List<Adopt> list = adoptService.selectAll(adopt);
        return Result.success(list);
    }

    @GetMapping("/selectChange")
    public Result selectChange() {
        List<Animal> list = adoptService.selectChange();
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Adopt adopt,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Adopt> page = adoptService.selectPage(adopt, pageNum, pageSize);
        return Result.success(page);
    }

}