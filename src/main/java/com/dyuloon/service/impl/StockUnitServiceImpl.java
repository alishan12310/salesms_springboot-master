package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.StockUnit;
import com.dyuloon.entity.StockUnit;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockUnitMapper;
import com.dyuloon.mapper.StockUnitMapper;
import com.dyuloon.service.StockUnitService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Service
public class StockUnitServiceImpl extends ServiceImpl<StockUnitMapper, StockUnit> implements StockUnitService {

    @Autowired(required = false)
    private StockUnitMapper stockUnitMapper;

    @Override
    public PageVO queryUnit(SearchForm searchForm) {
        System.out.println(searchForm);
        Page<StockUnit> storePage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<StockUnit> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<StockUnit> queryWrapper = new QueryWrapper<>();
        if(searchForm.getValue().equals("")){
            resultPage = this.stockUnitMapper.selectPage(storePage,queryWrapper.orderByDesc("unit_id"));
        }else {
            queryWrapper.like("unit_name",searchForm.getValue());
            resultPage = this.stockUnitMapper.selectPage(storePage,queryWrapper.orderByDesc("unit_id"));
        }
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }

    @Override
    public List queryAllUnit() {
        QueryWrapper<StockUnit> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("unit_id", "unit_name");
        List<StockUnit> queryAllUnit = stockUnitMapper.selectList(queryWrapper);
        return queryAllUnit;
    }

}
