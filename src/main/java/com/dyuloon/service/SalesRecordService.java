package com.dyuloon.service;

import com.dyuloon.entity.SalesRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.entity.SalesRecordEntity.ShowDayDataParameter;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-22
 */
public interface SalesRecordService extends IService<SalesRecord> {

    PageVO getScarceRecord(SearchForm searchForm);

    ResultVO addScarceRecord(List<SalesRecord> salesRecord);

    ResultVO deleteScarceRecord(Integer id);

    ResultVO updateScarceRecord(SalesRecord salesRecord);

    PageVO getAllScarceRecord(SearchForm searchForm);

    ResultVO deleteAll(String id);

    // 可视化
    // 格式化今年的数据
    ResultVO getFormatYear(Integer id);

    // 格式化一个月的每一天
    ResultVO getFormatDay(ShowDayDataParameter showDayDataParameter);

    // 上月各店铺销售单数
    ResultVO getStoreOrders();

    // 热销货物
    ResultVO getSellingGoods();

    // 新增销售趋势
    ResultVO getSaleTrend();

    // 今年总销售额
    ResultVO getNowtotalSales();

    //年销售总额统计
    ResultVO getNowAlltotalSales();

    // 上月各店铺销售单数
    ResultVO getSalesOrders();

    // 获取员工
    ResultVO getObtainEmployees();

    // 上月员工销售数据
    ResultVO getEmployeeData();

    // 员工当年销售额
    ResultVO getRevenueEmployees(Integer id);

    // 员工新增销售额趋势
    ResultVO getSalesTrends(Integer id);

    // 员工上月销售额
    ResultVO getSalesRevenue(Integer id);
}
