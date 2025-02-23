package com.dyuloon.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dyuloon.entity.StockUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockUnitMapper;
import com.dyuloon.vo.PageVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
public interface StockUnitService extends IService<StockUnit> {
    public PageVO queryUnit(SearchForm searchForm);
    public List queryAllUnit();
}
