package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.entity.User;
import com.dyuloon.from.RuleFrom;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StoreStoremanageMapper;
import com.dyuloon.mapper.UserMapper;
import com.dyuloon.service.StoreStoremanageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-19
 */
@Service
public class StoreStoremanageServiceImpl extends ServiceImpl<StoreStoremanageMapper, StoreStoremanage> implements StoreStoremanageService {

    @Autowired(required = false)
    private StoreStoremanageMapper storeStoremanageMapper;

    @Override
    public PageVO queryStore(SearchForm searchForm) {
        Page storePage = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        QueryWrapper<StoreStoremanage> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        this.storeStoremanageMapper.selectPage(storePage, queryWrapper.orderByDesc("storemanage_id"));
        PageVO pageVO = PageVOUtil.success(storePage.getRecords(), "查询成功！", storePage.getTotal(), storePage.getCurrent(), storePage.getSize());
        return pageVO;
    }
}
