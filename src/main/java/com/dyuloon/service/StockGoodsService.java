package com.dyuloon.service;

import com.dyuloon.entity.StockGoods;
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
public interface StockGoodsService extends IService<StockGoods> {
    public PageVO queryGoods(SearchForm searchForm);

    // 查所有货物
    ResultVO queryAllGoods();

    // 查货物详情
    ResultVO queryGoodsDetails(Integer id);
}
