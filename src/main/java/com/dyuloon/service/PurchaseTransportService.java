package com.dyuloon.service;

import com.dyuloon.entity.MenuManage;
import com.dyuloon.entity.PurchaseTransport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.entity.PurchaseTransportEntity.DelObj;
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
public interface PurchaseTransportService extends IService<PurchaseTransport> {

    ResultVO addPurchaseTransport(PurchaseTransport purchaseTransport);

    PageVO findPurchaseTransport(SearchForm searchForm);

    ResultVO deletePurchaseTransport(DelObj delObj);

    ResultVO successPurchaseTransport(DelObj delObj);
}
