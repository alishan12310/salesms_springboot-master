package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.MenuManageEntity.OnlyOneList;
import com.dyuloon.entity.StockCategory;
import com.dyuloon.entity.StockCategoryEntity.AllCategory;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockCategoryMapper;
import com.dyuloon.mapper.StoreStoremanageMapper;
import com.dyuloon.service.StockCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Service
public class StockCategoryServiceImpl extends ServiceImpl<StockCategoryMapper, StockCategory> implements StockCategoryService {

    @Autowired(required = false)
    private StockCategoryMapper stockCategoryMapper;

    @Override
    public PageVO queryCategory(SearchForm searchForm) {
//        System.out.println(searchForm);
        Page<StockCategory> storePage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<StockCategory> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<StockCategory> queryWrapper = new QueryWrapper<>();
        if(searchForm.getValue().equals("")){
            resultPage = this.stockCategoryMapper.selectPage(storePage,queryWrapper.orderByDesc("category_id"));
        }else {
            queryWrapper.like("category_name",searchForm.getValue());
            resultPage = this.stockCategoryMapper.selectPage(storePage,queryWrapper.orderByDesc("category_id"));
        }
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }

    @Override
    public List queryAllCategory(){
        QueryWrapper<StockCategory> queryWrapper = new QueryWrapper<>();
//        queryWrapper.select("category_id", "category_name");
        List<StockCategory> queryAllCategory = stockCategoryMapper.selectList(queryWrapper);
        List<AllCategory> finalList = new ArrayList<>();
        for (int i = 0; i < queryAllCategory.size(); i++) {
            AllCategory list = new AllCategory();
            list.setCategoryId(queryAllCategory.get(i).getCategoryId());
            list.setCategoryName(queryAllCategory.get(i).getCategoryName());
            finalList.add(list);
        }
        return finalList;
    }
}
