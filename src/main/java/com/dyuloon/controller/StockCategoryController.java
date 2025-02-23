package com.dyuloon.controller;


import com.dyuloon.entity.StockCategory;
import com.dyuloon.entity.StockCategoryEntity.AllCategory;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.StockCategoryService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
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
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/stockCategory")
public class StockCategoryController {

    @Autowired
    private StockCategoryService stockCategoryService;

    // 添加货物类别
    @PostMapping("/saveCategory")
    public ResultVO saveStore(@RequestBody StockCategory StockCategory) {
//        System.out.println("111111111"+user);
        boolean save = this.stockCategoryService.save(StockCategory);
        ResultVO resultVO = null;
        if (!save) {
            resultVO = ResultVOUtil.fail("添加失败！");
        } else {
            resultVO = ResultVOUtil.success(null, "添加成功！");
        }
        return resultVO;
    }

    // 查询货物类别
    @GetMapping("queryCategory")
    public ResultVO queryEmployee(SearchForm searchForm){
        PageVO pageVO = this.stockCategoryService.queryCategory(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }

    // 查询所有货物类别
    @GetMapping("queryAllCategory")
    public ResultVO queryAllCategory(){
        List<AllCategory> stockCategoryList = this.stockCategoryService.queryAllCategory();
        return ResultVOUtil.success(stockCategoryList,"查询成功！");
    }

    // 修改货物类别
    @PutMapping("/updateCategory")
    public ResultVO updateEmployee(@RequestBody StockCategory stockCategory){
        boolean update = this.stockCategoryService.updateById(stockCategory);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }

    // 删除货物类别
    @DeleteMapping("/deleteCategory")
    public ResultVO deleteEmployee(StockCategory stockCategory){
        boolean remove = this.stockCategoryService.removeById(stockCategory.getCategoryId());
        ResultVO resultVO = null;
        if(!remove){
            resultVO = ResultVOUtil.fail("删除失败！");
        }else
            resultVO = ResultVOUtil.success(null,"删除成功！");
        return resultVO;
    }
}

