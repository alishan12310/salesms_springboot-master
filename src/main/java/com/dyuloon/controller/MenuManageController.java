package com.dyuloon.controller;


import com.dyuloon.entity.MenuManage;
import com.dyuloon.entity.MenuManageEntity.OnlyTwoList;
import com.dyuloon.entity.MenuManageEntity.levelOneList;
import com.dyuloon.entity.MenuManageEntity.OnlyOneList;
import com.dyuloon.service.MenuManageService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-07
 */
@RestController
@RequestMapping("/menuManages")
public class MenuManageController {

    @Autowired
    private MenuManageService menuManageService;

    // 返回菜单列表
    @GetMapping("{id}")
    public ResultVO getAllMenuList(@PathVariable Integer id){
        List<levelOneList> list = menuManageService.getAllMenuList(id);
        return ResultVOUtil.success(list,"查询成功！");
    }

    // 返回所有一级菜单列表
    @GetMapping("getAllOneMenuList")
    public ResultVO getAllOneMenuList(){
        List<OnlyOneList> list = menuManageService.getAllOneMenuList();
        return ResultVOUtil.success(list,"查询成功！");
    }

    // 返回所有二级菜单列表
    @GetMapping("getAllTwoMenuList/{id}")
    public ResultVO getAllTwoMenuList(@PathVariable Integer id){
        List<OnlyTwoList> list = menuManageService.getAllTwoMenuList(id);
        return ResultVOUtil.success(list,"查询成功！");
    }

    // 添加菜单列表
    @PostMapping()
    public ResultVO addMenuList(@RequestBody MenuManage menuManage){
        ResultVO resultVO = menuManageService.addMenuList(menuManage);
        return resultVO;
    }

    // 修改菜单列表
    @PutMapping()
    public ResultVO updateMenuList(@RequestBody MenuManage menuManage){
        ResultVO resultVO = menuManageService.updateMenuList(menuManage);
        return resultVO;
    }

    // 删除菜单列表
    @DeleteMapping("{id}")
    public ResultVO deleteMenuList(@PathVariable Integer id){
        ResultVO  resultVO= menuManageService.deleteMenuList(id);
        return resultVO;
    }

}

