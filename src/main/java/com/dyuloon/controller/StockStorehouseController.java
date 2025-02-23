package com.dyuloon.controller;


import com.dyuloon.entity.StockStorehouse;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.StockStorehouseService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/stockStorehouse")
public class StockStorehouseController {

    @Autowired
    private StockStorehouseService stockStorehouseService;

    // 添加货物单位
    @PostMapping("/saveStorehouse")
    public ResultVO saveStore(@RequestBody StockStorehouse StockStorehouse) {
//        System.out.println("111111111"+user);
        boolean save = this.stockStorehouseService.save(StockStorehouse);
        ResultVO resultVO = null;
        if (!save) {
            resultVO = ResultVOUtil.fail("添加失败！");
        } else {
            resultVO = ResultVOUtil.success(null, "添加成功！");
        }
        return resultVO;
    }

    // 查询货物单位
    @GetMapping("queryStorehouse")
    public ResultVO queryEmployee(SearchForm searchForm){
        PageVO pageVO = this.stockStorehouseService.queryStorehouse(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }

    // 修改货物单位
    @PutMapping("/updateStorehouse")
    public ResultVO updateEmployee(@RequestBody StockStorehouse stockStorehouse){
        boolean update = this.stockStorehouseService.updateById(stockStorehouse);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }

    // 删除货物单位
    @DeleteMapping("/deleteStorehouse")
    public ResultVO deleteEmployee(StockStorehouse stockStorehouse){
        boolean remove = this.stockStorehouseService.removeById(stockStorehouse.getStorehouseId());
        ResultVO resultVO = null;
        if(!remove){
            resultVO = ResultVOUtil.fail("删除失败！");
        }else
            resultVO = ResultVOUtil.success(null,"删除成功！");
        return resultVO;
    }
}

