package com.dyuloon.controller;


import com.dyuloon.entity.StockGoods;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.StockGoodsService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/stockGoods")
public class StockGoodsController {

    @Autowired
    private StockGoodsService stockGoodsService;

    // 查询货物
    @GetMapping("/queryGoods")
    public ResultVO queryGoods(SearchForm searchForm){
        PageVO pageVO = this.stockGoodsService.queryGoods(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }

    // 查所有货物
    @GetMapping("/queryAllGoods")
    public ResultVO queryAllGoods(){
        ResultVO resultVO = this.stockGoodsService.queryAllGoods();
        return resultVO;
    }

    // 查货物详情
    @GetMapping("/queryGoodsDetails/{id}")
    public ResultVO queryGoodsDetails(@PathVariable Integer id){
        ResultVO resultVO = this.stockGoodsService.queryGoodsDetails(id);
        return resultVO;
    }

    // 添加货物
    @PostMapping("/saveGoods")
    public ResultVO addGoods(@RequestBody StockGoods stockGoods) {
//        System.out.println("111111111"+Goods);
        boolean addGoods = this.stockGoodsService.save(stockGoods);
        ResultVO resultVO = null;
        if (!addGoods) {
            resultVO = ResultVOUtil.fail("添加失败！");
        } else {
            resultVO = ResultVOUtil.success(null, "添加成功！");
        }
        return resultVO;
    }

    // 修改店铺
    @PutMapping("/updateGoods")
    public ResultVO updateGoods(@RequestBody StockGoods stockGoods){
        boolean update = this.stockGoodsService.updateById(stockGoods);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }
    
    // 删除货物
    @DeleteMapping("/deleteGoods")
    public ResultVO deleteGoods(StockGoods stockGoods){
        boolean remove = this.stockGoodsService.removeById(stockGoods.getGoodsId());
        ResultVO resultVO = null;
        if(!remove){
            resultVO = ResultVOUtil.fail("删除失败！");
        }else
            resultVO = ResultVOUtil.success(null,"删除成功！");
        return resultVO;
    }
}

