package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.PurchaseOrder;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.PurchaseOrderMapper;
import com.dyuloon.service.PurchaseOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

    @Autowired(required = false)
    private PurchaseOrderMapper purchaseOrderMapper;

    @Override
    public PageVO queryOrder(SearchForm searchForm) {
//        System.out.println(searchForm);
        Page<PurchaseOrder> orderPage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<PurchaseOrder> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<PurchaseOrder> queryWrapper = new QueryWrapper<>();
        if(searchForm.getFlag() == 1){
            queryWrapper.eq("order_state",0);
        }
        if(searchForm.getValue().equals("")){
            resultPage = this.purchaseOrderMapper.selectPage(orderPage,queryWrapper.orderByDesc("order_id"));
        }else {
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.purchaseOrderMapper.selectPage(orderPage,queryWrapper.orderByDesc("order_id"));
        }
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }
}
