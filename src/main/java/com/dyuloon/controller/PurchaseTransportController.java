package com.dyuloon.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.MenuManage;
import com.dyuloon.entity.PurchaseTransport;
import com.dyuloon.entity.PurchaseTransportEntity.DelObj;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.PurchaseOrderService;
import com.dyuloon.service.PurchaseTransportService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/purchaseTransports")
public class PurchaseTransportController {

    @Autowired
    private PurchaseTransportService purchaseTransportService;

    // 新增
    @PostMapping()
    public ResultVO addPurchaseTransport(@RequestBody PurchaseTransport purchaseTransport) {
        ResultVO resultVO = purchaseTransportService.addPurchaseTransport(purchaseTransport);
        return resultVO;
    }

    // 查所有
    @GetMapping()
    public PageVO findPurchaseTransport(SearchForm searchForm) {
        PageVO pageVO = this.purchaseTransportService.findPurchaseTransport(searchForm);
        return pageVO;
    }

    // 修改
    @PutMapping
    public ResultVO updatePurchaseTransport(@RequestBody PurchaseTransport purchaseTransport){
        boolean update = this.purchaseTransportService.updateById(purchaseTransport);
        ResultVO resultVO = update ? ResultVOUtil.success(null,"更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    // 删除
    @DeleteMapping()
    public ResultVO deletePurchaseTransport(DelObj delObj){
        ResultVO resultVO = this.purchaseTransportService.deletePurchaseTransport(delObj);
        return resultVO;
    }

    // 完成物流运输订单
    @PutMapping("/successPurchaseTransport")
    public ResultVO successPurchaseTransport(@RequestBody DelObj delObj){
        System.out.println(delObj.toString());
        ResultVO resultVO = this.purchaseTransportService.successPurchaseTransport(delObj);
        return resultVO;
    }
}

