package com.dyuloon.controller;


import com.dyuloon.entity.MenuManageEntity.levelOneList;
import com.dyuloon.entity.SalesRecord;
import com.dyuloon.entity.SalesRecordEntity.ShowDayDataParameter;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.SalesRecordService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@RestController
@RequestMapping("/salesRecords")
public class SalesRecordController {

    @Autowired
    private SalesRecordService salesRecordService;

    // 缺货记录
    @GetMapping("getScarceRecord")
    public PageVO getScarceRecord(SearchForm searchForm){
        PageVO pageVO = salesRecordService.getScarceRecord(searchForm);
        return pageVO;
    }

    // 新增销售记录
    @PostMapping
    public ResultVO addScarceRecord(@RequestBody List<SalesRecord> salesRecord){
        ResultVO resultVO = salesRecordService.addScarceRecord(salesRecord);
        return resultVO;
    }

    // 删除
    @DeleteMapping("{id}")
    public ResultVO deleteScarceRecord(@PathVariable Integer id){
        ResultVO resultVO = salesRecordService.deleteScarceRecord(id);
        return resultVO;
    }

    // 删除订单
    @DeleteMapping("deleteAll/{id}")
    public ResultVO deleteAll(@PathVariable String id){
        ResultVO resultVO = salesRecordService.deleteAll(id);
        return resultVO;
    }

    // 修改
    @PutMapping
    public ResultVO updateScarceRecord(@RequestBody SalesRecord salesRecord){
        ResultVO resultVO = salesRecordService.updateScarceRecord(salesRecord);
        return resultVO;
    }

    // 查询
    @GetMapping()
    public PageVO getAllScarceRecord(SearchForm searchForm){
        PageVO pageVO = salesRecordService.getAllScarceRecord(searchForm);
        return pageVO;
    }

    // 可视化
    // 格式化年
    @GetMapping("/getFormatYear/{id}")
    public ResultVO getFormatYear(@PathVariable Integer id){
        ResultVO resultVO = salesRecordService.getFormatYear(id);
        return resultVO;
    }

    // 格式化一个月的每一天
    @GetMapping("/getFormatDay")
    public ResultVO getFormatDay(ShowDayDataParameter showDayDataParameter){
        ResultVO resultVO = salesRecordService.getFormatDay(showDayDataParameter);
        return resultVO;
    }

    // 上月各店铺销售单数
    @GetMapping("/getStoreOrders")
    public ResultVO getStoreOrders(){
        ResultVO resultVO = salesRecordService.getStoreOrders();
        return resultVO;
    }

    // 热销货物
    @GetMapping("/getSellingGoods")
    public ResultVO getSellingGoods(){
        ResultVO resultVO = salesRecordService.getSellingGoods();
        return resultVO;
    }

    // 新增销售趋势
    @GetMapping("/getSaleTrend")
    public ResultVO getSaleTrend(){
        ResultVO resultVO = salesRecordService.getSaleTrend();
        return resultVO;
    }

    // 今年总销售额
    @GetMapping("/getNowtotalSales")
    public ResultVO getNowtotalSales(){
        ResultVO resultVO = salesRecordService.getNowtotalSales();
        return resultVO;
    }

    //年销售总额统计
    @GetMapping("/getNowAlltotalSales")
    public ResultVO getNowAlltotalSales(){
        ResultVO resultVO = salesRecordService.getNowAlltotalSales();
        return resultVO;
    }

    // 上月各店铺销售单数
    @GetMapping("/getSalesOrders")
    public ResultVO getSalesOrders(){
        ResultVO resultVO = salesRecordService.getSalesOrders();
        return resultVO;
    }

    // 可视化员工页面
    // 获取员工
    @GetMapping("/getObtainEmployees")
    public ResultVO getObtainEmployees(){
        ResultVO resultVO = salesRecordService.getObtainEmployees();
        return resultVO;
    }

    // 上月员工销售数据
    @GetMapping("/getEmployeeData")
    public ResultVO getEmployeeData(){
        ResultVO resultVO = salesRecordService.getEmployeeData();
        return resultVO;
    }

    // 员工当年销售额
    @GetMapping("/getRevenueEmployees/{id}")
    public ResultVO getRevenueEmployees(@PathVariable Integer id){
        ResultVO resultVO = salesRecordService.getRevenueEmployees(id);
        return resultVO;
    }

    // 员工新增销售额趋势
    @GetMapping("/getSalesTrends/{id}")
    public ResultVO getSalesTrends(@PathVariable Integer id){
        ResultVO resultVO = salesRecordService.getSalesTrends(id);
        return resultVO;
    }

    // 员工上月销售额
    @GetMapping("/getSalesRevenue/{id}")
    public ResultVO getSalesRevenue(@PathVariable Integer id){
        ResultVO resultVO = salesRecordService.getSalesRevenue(id);
        return resultVO;
    }
}

