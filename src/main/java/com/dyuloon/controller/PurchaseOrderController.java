package com.dyuloon.controller;


import com.dyuloon.entity.PurchaseOrder;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.PurchaseOrderService;
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
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    // 查询订单
    @GetMapping("/queryOrder")
    public ResultVO queryOrder(SearchForm searchForm){
        PageVO pageVO = this.purchaseOrderService.queryOrder(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }
    // 添加订单
    @PostMapping("/saveOrder")
    public ResultVO saveOrder(@RequestBody PurchaseOrder purchaseOrder) {
        boolean save = this.purchaseOrderService.save(purchaseOrder);
        ResultVO resultVO = null;
        if (!save) {
            resultVO = ResultVOUtil.fail("添加失败！");
        } else {
            resultVO = ResultVOUtil.success(null, "添加成功！");
        }
        return resultVO;
    }

    // 删除订单
    @DeleteMapping("{id}")
    public ResultVO deleteOrder(@PathVariable Integer id){
        boolean remove = this.purchaseOrderService.removeById(id);
        ResultVO resultVO = remove ? ResultVOUtil.success(null,"删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }
    
    // 修改订单
    @PutMapping("/updateOrder")
    public ResultVO updateOrder(@RequestBody PurchaseOrder purchaseOrder){
        boolean update = this.purchaseOrderService.updateById(purchaseOrder);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }
}

