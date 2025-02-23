package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.*;
import com.dyuloon.entity.PurchaseTransportEntity.DelObj;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.PurchaseOrderMapper;
import com.dyuloon.mapper.PurchaseTransportMapper;
import com.dyuloon.mapper.StockGoodsMapper;
import com.dyuloon.mapper.StockStorehouseMapper;
import com.dyuloon.service.PurchaseOrderService;
import com.dyuloon.service.PurchaseTransportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Service
public class PurchaseTransportServiceImpl extends ServiceImpl<PurchaseTransportMapper, PurchaseTransport> implements PurchaseTransportService {

    @Autowired(required = false)
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired(required = false)
    StockStorehouseMapper stockStorehouseMapper;
    @Autowired(required = false)
    StockGoodsMapper stockGoodsMapper;

    // 新增
    @Override
    public ResultVO addPurchaseTransport(PurchaseTransport purchaseTransport) {
        UpdateWrapper luw = new UpdateWrapper();
        luw.eq("order_number", purchaseTransport.getTransportOrdernumber());
        luw.set("order_state", 1);
        int update = purchaseOrderMapper.update(null, luw);
        int save = this.baseMapper.insert(purchaseTransport);
        ResultVO resultVO = ((save == 1) && (update == 1)) ? ResultVOUtil.success(null, "添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }
    // 查找
    @Override
    public PageVO findPurchaseTransport(SearchForm searchForm) {
        Page page = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        QueryWrapper<PurchaseTransport> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        Page oneList =this.baseMapper.selectPage(page, queryWrapper.orderByDesc("transport_id"));
        List<PurchaseTransport> finalList = oneList.getRecords();
        for (int i = 0; i < finalList.size(); i++) {
            LambdaQueryWrapper<StockStorehouse> lqw = new LambdaQueryWrapper<>();
            lqw.eq(StockStorehouse::getStorehouseId,finalList.get(i).getTransportStoragehouse());
            StockStorehouse stockStorehouse = stockStorehouseMapper.selectOne(lqw);
            System.out.println(stockStorehouse+"------------2");
            finalList.get(i).setTransportStoragehouse(stockStorehouse.getStorehouseName());
        }
        page.setRecords(finalList);
        PageVO pageVO = PageVOUtil.success(page.getRecords(), "查询成功！", page.getTotal(), page.getCurrent(), page.getSize());
        return pageVO;
    }
    // 删除
    @Override
    public ResultVO deletePurchaseTransport(DelObj delObj) {
        UpdateWrapper luw = new UpdateWrapper();
        luw.eq("order_number", delObj.getTransportOrdernumber());
        luw.set("order_state", 0);
        int update = purchaseOrderMapper.update(null, luw);
        int remove = this.baseMapper.deleteById(delObj.getTransportId());
        ResultVO resultVO = ((remove == 1) && (update == 1)) ?  ResultVOUtil.fail("删除失败！") : ResultVOUtil.success(null, "删除成功！");
        return resultVO;
    }

    // 完成物流运输订单
    @Override
    public ResultVO successPurchaseTransport(DelObj delObj) {
        UpdateWrapper luw = new UpdateWrapper();
        luw.eq("order_number", delObj.getTransportOrdernumber());
        luw.set("order_state", 2);
        int updateOrder = purchaseOrderMapper.update(null, luw);
        UpdateWrapper luw2 = new UpdateWrapper();
        luw2.eq("transport_id", delObj.getTransportId());
        luw2.set("transport_state", 1);
        int updateTransport = this.baseMapper.update(null,luw2);

        // 查库房
        PurchaseTransport purchaseTransport = this.baseMapper.selectById(delObj.getTransportId());
        // 查ing但货物
        LambdaQueryWrapper<PurchaseOrder> lqw = new LambdaQueryWrapper<>();
        lqw.eq(PurchaseOrder::getOrderNumber,delObj.getTransportOrdernumber());
        PurchaseOrder purchaseOrder = this.purchaseOrderMapper.selectOne(lqw);

        // 新增存货
        // 存货列表
        List<StockGoods> stockGoodsList = new ArrayList<>();
        // 拆字符串塞进存货列表
        String[] list01 = purchaseOrder.getOrderCategory().split(",");
        String[] list02 = purchaseOrder.getOrderGoods().split(",");
        String[] list03 = purchaseOrder.getOrderNum().split(",");
        String[] list04 = purchaseOrder.getOrderUnit().split(",");
        String[] list05 = purchaseOrder.getOrderPrice().split(",");
        for (int i = 0; i < list01.length; i++) {
            StockGoods stockGoods = new StockGoods();
            stockGoods.setGoodsStorehouse(purchaseTransport.getTransportStoragehouse());
            stockGoods.setGoodsCategory(list01[i]);
            stockGoods.setGoodsName(list02[i]);
            stockGoods.setGoodsQuantity(Integer.parseInt(list03[i]));
            stockGoods.setGoodsUnit(list04[i]);
            stockGoods.setGoodsPrice(list05[i]);
            stockGoodsList.add(stockGoods);
        }
        for (int j = 0; j < stockGoodsList.size(); j++) {
            this.stockGoodsMapper.insert(stockGoodsList.get(j));
        }

        ResultVO resultVO = ((updateTransport == 1) && (updateOrder == 1)) ? ResultVOUtil.success(null, "运输状态变更成功！") : ResultVOUtil.fail("运输状态变更失败！");
        return resultVO;
    }
}
