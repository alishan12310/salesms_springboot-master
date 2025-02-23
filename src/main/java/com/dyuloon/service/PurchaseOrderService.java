package com.dyuloon.service;

import com.dyuloon.entity.MenuManage;
import com.dyuloon.entity.PurchaseOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
public interface PurchaseOrderService extends IService<PurchaseOrder> {
    public PageVO queryOrder(SearchForm searchForm);
}
