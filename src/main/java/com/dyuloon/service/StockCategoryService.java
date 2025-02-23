package com.dyuloon.service;

import com.dyuloon.entity.StockCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
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
public interface StockCategoryService extends IService<StockCategory> {
    public PageVO queryCategory(SearchForm searchForm);
    public List queryAllCategory();

}
