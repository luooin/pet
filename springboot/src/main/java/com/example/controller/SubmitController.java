package com.example.controller;

import com.example.common.Result;
import com.example.entity.Submit;
import com.example.service.SubmitService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 上报信息表表前端操作接口
 **/
@RestController
@RequestMapping("/submit")
public class SubmitController {

    @Resource
    private SubmitService submitService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Submit submit) {
        submitService.add(submit);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        submitService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        submitService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Submit submit) {
        submitService.updateById(submit);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Submit submit = submitService.selectById(id);
        return Result.success(submit);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Submit submit ) {
        List<Submit> list = submitService.selectAll(submit);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Submit submit,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Submit> page = submitService.selectPage(submit, pageNum, pageSize);
        return Result.success(page);
    }

}