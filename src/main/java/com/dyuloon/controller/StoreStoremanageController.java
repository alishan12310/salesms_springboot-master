package com.dyuloon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.entity.User;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.StoreStoremanageService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-19
 */
@RestController
@RequestMapping("/storeStoremanages")
public class StoreStoremanageController {

    @Autowired
    private StoreStoremanageService storeStoremanageService;

    // 添加店铺
    @PostMapping
    public ResultVO saveStore(@RequestBody StoreStoremanage storeStoremanage) {
        boolean save = this.storeStoremanageService.save(storeStoremanage);
        ResultVO resultVO = save ? ResultVOUtil.success(null,"添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }

    // 查询店铺
    @GetMapping
    public ResultVO queryStore(SearchForm searchForm) {
        PageVO pageVO = this.storeStoremanageService.queryStore(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }

    // 查询店名
    @GetMapping("queryStoreName")
    public ResultVO queryStoreName(){
        List<StoreStoremanage> storeStoremanageList = this.storeStoremanageService.list();
        return ResultVOUtil.success(storeStoremanageList,"查询成功");
    }

    // 修改店铺
    @PutMapping
    public ResultVO updateStore(@RequestBody StoreStoremanage storeStoremanage){
        boolean update = this.storeStoremanageService.updateById(storeStoremanage);
        ResultVO resultVO = update ? ResultVOUtil.success(null,"更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    // 删除店铺
    @DeleteMapping("{id}")
    public ResultVO deleteStore(@PathVariable Integer id){
        boolean remove = this.storeStoremanageService.removeById(id);
        ResultVO resultVO = remove ? ResultVOUtil.success(null,"删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }
}

