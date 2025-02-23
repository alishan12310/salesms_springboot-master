package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.StockCategory;
import com.dyuloon.entity.StockGoods;
import com.dyuloon.entity.StockGoods;
import com.dyuloon.entity.StockGoodsEntity.OneGoodsList;
import com.dyuloon.entity.StockGoodsEntity.TwoGoodsList;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockCategoryMapper;
import com.dyuloon.mapper.StockGoodsMapper;
import com.dyuloon.mapper.StockGoodsMapper;
import com.dyuloon.mapper.StoreStoremanageMapper;
import com.dyuloon.service.StockGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.BeanUtils;
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
public class StockGoodsServiceImpl extends ServiceImpl<StockGoodsMapper, StockGoods> implements StockGoodsService {

    @Autowired(required = false)
    private StockGoodsMapper stockGoodsMapper;

    @Autowired(required = false)
    private StockCategoryMapper stockCategoryMapper;

    @Override
    public PageVO queryGoods(SearchForm searchForm) {
//        System.out.println(searchForm);
        Page<StockGoods> storePage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<StockGoods> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<StockGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("goods_storehouse", searchForm.getFlag());
        if(searchForm.getValue().equals("")){
            resultPage = this.stockGoodsMapper.selectPage(storePage,queryWrapper.orderByDesc("goods_id"));
        }else {
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
            resultPage = this.stockGoodsMapper.selectPage(storePage,queryWrapper.orderByDesc("goods_id"));
        }
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }

    @Override
    public ResultVO queryAllGoods() {
        LambdaQueryWrapper<StockCategory> lqwOne = new LambdaQueryWrapper<>();
        lqwOne.orderByDesc(StockCategory::getCategoryId);
        // 查类别
        List<StockCategory> stockCategoryList = this.stockCategoryMapper.selectList(lqwOne);
        // 查货物
        List<StockGoods> stockGoodsList = this.baseMapper.selectList(null);
        // 最终输出结果
        List<OneGoodsList> finalList = new ArrayList<>();

        for (int i = 0; i < stockCategoryList.size(); i++) {
            StockCategory stockCategory = stockCategoryList.get(i);
            OneGoodsList oneGoodsList = new OneGoodsList();
            oneGoodsList.setGoodsId(stockCategory.getCategoryId());
            oneGoodsList.setGoodsName(stockCategory.getCategoryName());
            finalList.add(oneGoodsList);

            List<TwoGoodsList> twoGoodsLists = new ArrayList<>();

            for (int j = 0; j < stockGoodsList.size() ; j++) {
                StockGoods stockGoods = stockGoodsList.get(j);
                if(stockGoods.getGoodsCategory().equals(stockCategory.getCategoryName())){
                    TwoGoodsList twoGoodsList = new TwoGoodsList();
                    BeanUtils.copyProperties(stockGoods,twoGoodsList);
                    twoGoodsLists.add(twoGoodsList);
                }
            }
            oneGoodsList.setChildren(twoGoodsLists);
        }
        ResultVO resultVO = finalList != null ? ResultVOUtil.success(finalList,"查询成功！") : ResultVOUtil.fail("查询失败！");
        return resultVO;
    }

    @Override
    public ResultVO queryGoodsDetails(Integer id) {
        LambdaQueryWrapper<StockGoods> lqw = new LambdaQueryWrapper<>();
        lqw.eq(StockGoods::getGoodsId,id);
        StockGoods data = this.baseMapper.selectOne(lqw);
        ResultVO resultVO = data != null ? ResultVOUtil.success(data,"查询成功！") : ResultVOUtil.fail("查询失败！");
        return resultVO;
    }
}
