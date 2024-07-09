package com.example.controller;

import com.example.common.Result;
import com.example.entity.Foster;
import com.example.service.FosterService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 寄养信息表前端操作接口
 **/
@RestController
@RequestMapping("/foster")
public class FosterController {

    @Resource
    private FosterService fosterService;

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Foster foster) {
        fosterService.add(foster);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        fosterService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        fosterService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Foster foster) {
        fosterService.updateById(foster);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Foster foster = fosterService.selectById(id);
        return Result.success(foster);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Foster foster ) {
        List<Foster> list = fosterService.selectAll(foster);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Foster foster,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<Foster> page = fosterService.selectPage(foster, pageNum, pageSize);
        return Result.success(page);
    }

}