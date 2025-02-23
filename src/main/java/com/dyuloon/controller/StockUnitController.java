package com.dyuloon.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dyuloon.entity.StockUnit;
import com.dyuloon.entity.StockUnit;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockUnitMapper;
import com.dyuloon.service.StockUnitService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/stockUnit")
public class StockUnitController {

    @Autowired
    private StockUnitService stockUnitService;

    // 添加货物单位
    @PostMapping("/saveUnit")
    public ResultVO saveStore(@RequestBody StockUnit StockUnit) {
//        System.out.println("111111111"+user);
        boolean save = this.stockUnitService.save(StockUnit);
        ResultVO resultVO = null;
        if (!save) {
            resultVO = ResultVOUtil.fail("添加失败！");
        } else {
            resultVO = ResultVOUtil.success(null, "添加成功！");
        }
        return resultVO;
    }

    // 查询货物单位
    @GetMapping("queryUnit")
    public ResultVO queryEmployee(SearchForm searchForm){
        PageVO pageVO = this.stockUnitService.queryUnit(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }
    
    // 查询全部货物单位
    @GetMapping("queryAllUnit")
    public ResultVO queryEmployee(){
        List<StockUnit> queryAllUnit = this.stockUnitService.queryAllUnit();
        return ResultVOUtil.success(queryAllUnit,"查询成功！");
    }

    // 修改货物单位
    @PutMapping("/updateUnit")
    public ResultVO updateEmployee(@RequestBody StockUnit stockUnit){
        boolean update = this.stockUnitService.updateById(stockUnit);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }

    // 删除货物单位
    @DeleteMapping("/deleteUnit")
    public ResultVO deleteEmployee(StockUnit stockUnit){
        boolean remove = this.stockUnitService.removeById(stockUnit.getUnitId());
        ResultVO resultVO = null;
        if(!remove){
            resultVO = ResultVOUtil.fail("删除失败！");
        }else
            resultVO = ResultVOUtil.success(null,"删除成功！");
        return resultVO;
    }
}

