package com.dyuloon.service;

import com.dyuloon.entity.StoreStoremanage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-19
 */
public interface StoreStoremanageService extends IService<StoreStoremanage> {
    PageVO queryStore(SearchForm searchForm);
}
