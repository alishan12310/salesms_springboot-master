package com.dyuloon.service;

import com.dyuloon.entity.StockStorehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
public interface StockStorehouseService extends IService<StockStorehouse> {
    public PageVO queryStorehouse(SearchForm searchForm);
}
