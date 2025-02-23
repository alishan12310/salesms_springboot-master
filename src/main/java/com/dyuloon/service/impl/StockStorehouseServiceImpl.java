package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.StockStorehouse;
import com.dyuloon.entity.StockStorehouse;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockStorehouseMapper;
import com.dyuloon.mapper.StockStorehouseMapper;
import com.dyuloon.service.StockStorehouseService;
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
public class StockStorehouseServiceImpl extends ServiceImpl<StockStorehouseMapper, StockStorehouse> implements StockStorehouseService {

    @Autowired(required = false)
    private StockStorehouseMapper stockStorehouseMapper;

    @Override
    public PageVO queryStorehouse(SearchForm searchForm) {
        System.out.println(searchForm);
        Page<StockStorehouse> storePage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<StockStorehouse> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<StockStorehouse> queryWrapper = new QueryWrapper<>();
        if(searchForm.getValue().equals("")){
            resultPage = this.stockStorehouseMapper.selectPage(storePage,queryWrapper.orderByDesc("storehouse_id"));
        }else {
            queryWrapper.like("storehouse_name",searchForm.getValue());
            resultPage = this.stockStorehouseMapper.selectPage(storePage,queryWrapper.orderByDesc("storehouse_id"));
        }
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }
}
