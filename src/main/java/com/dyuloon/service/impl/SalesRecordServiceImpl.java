package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.entity.SalesRecord;
import com.dyuloon.entity.SalesRecordEntity.*;
import com.dyuloon.entity.StockGoods;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.entity.User;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.SalesRecordMapper;
import com.dyuloon.mapper.StockGoodsMapper;
import com.dyuloon.mapper.StoreStoremanageMapper;
import com.dyuloon.mapper.UserMapper;
import com.dyuloon.service.SalesRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-22
 */
@Service
public class SalesRecordServiceImpl extends ServiceImpl<SalesRecordMapper, SalesRecord> implements SalesRecordService {

    @Autowired(required = false)
    private StockGoodsMapper stockGoodsMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Autowired(required = false)
    private StoreStoremanageMapper storeStoremanageMapper;

    @Override
    public PageVO getScarceRecord(SearchForm searchForm) {

        Page storePage = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        QueryWrapper<StockGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        queryWrapper.le("goods_quantity", searchForm.getFlag());
        queryWrapper.orderByDesc("goods_id");
        this.stockGoodsMapper.selectPage(storePage, queryWrapper);
        PageVO pageVO = PageVOUtil.success(storePage.getRecords(), "查询成功！", storePage.getTotal(), storePage.getCurrent(), storePage.getSize());

        return pageVO;
    }

    // 新增
    @Override
    public ResultVO addScarceRecord(List<SalesRecord> salesRecord) {
        int save = 0;
        for (int i = 0; i < salesRecord.size(); i++) {
            // 存储
            save = save + this.baseMapper.insert(salesRecord.get(i));

            // 查货物
            StockGoods stockGoods = this.stockGoodsMapper.selectById(salesRecord.get(i).getRecordGoods());
            // 算剩余数量
            Integer oldOne = stockGoods.getGoodsQuantity();
            Integer newOne = Math.toIntExact(salesRecord.get(i).getRecordNum());
            Integer num = oldOne - newOne;
            LambdaUpdateWrapper<StockGoods> luw = new LambdaUpdateWrapper<>();
            luw.eq(StockGoods::getGoodsId, salesRecord.get(i).getRecordGoods());
            luw.set(StockGoods::getGoodsQuantity, num);
            this.stockGoodsMapper.update(null, luw);
        }
        ResultVO resultVO = save == salesRecord.size() ? ResultVOUtil.success(null, "添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }

    // 更新
    @Override
    public ResultVO updateScarceRecord(SalesRecord salesRecord) {
        SalesRecord salesRecordOld = this.baseMapper.selectById(salesRecord.getRecordGoods());
        if (salesRecordOld.getRecordGoods() != salesRecord.getRecordGoods()) {

        } else {

        }
        // 存储更新
        int update = this.baseMapper.updateById(salesRecord);
        ResultVO resultVO = update == 1 ? ResultVOUtil.success(null, "更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    // 删全部
    @Override
    public ResultVO deleteAll(String id) {
        // 查删掉的集合
        LambdaQueryWrapper<SalesRecord> lqw = new LambdaQueryWrapper<>();
        lqw.eq(SalesRecord::getRecordOrder, id);
        List<SalesRecord> salesRecord = this.baseMapper.selectList(lqw);
        for (int i = 0; i < salesRecord.size(); i++) {
            // 查货物
            StockGoods stockGoods = this.stockGoodsMapper.selectById(salesRecord.get(i).getRecordGoods());
            // 算剩余数量
            Integer oldOne = stockGoods.getGoodsQuantity();
            Integer newOne = Math.toIntExact(salesRecord.get(i).getRecordNum());
            Integer num = oldOne + newOne;
            LambdaUpdateWrapper<StockGoods> luw = new LambdaUpdateWrapper<>();
            luw.eq(StockGoods::getGoodsId, salesRecord.get(i).getRecordGoods());
            luw.set(StockGoods::getGoodsQuantity, num);
            this.stockGoodsMapper.update(null, luw);
        }

        // 最终删除
        LambdaQueryWrapper<SalesRecord> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(SalesRecord::getRecordOrder, id);
        int remove = this.baseMapper.delete(lqw2);

        ResultVO resultVO = remove != 0 ? ResultVOUtil.success(null, "删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }

    // 删单个
    @Override
    public ResultVO deleteScarceRecord(Integer id) {
        SalesRecord salesRecord = this.baseMapper.selectById(id);
        // 查货物
        StockGoods stockGoods = this.stockGoodsMapper.selectById(salesRecord.getRecordGoods());
        // 算剩余数量
        Integer oldOne = stockGoods.getGoodsQuantity();
        Integer newOne = Math.toIntExact(salesRecord.getRecordNum());
        Integer num = oldOne + newOne;
        LambdaUpdateWrapper<StockGoods> luw = new LambdaUpdateWrapper<>();
        luw.eq(StockGoods::getGoodsId, salesRecord.getRecordGoods());
        luw.set(StockGoods::getGoodsQuantity, num);
        this.stockGoodsMapper.update(null, luw);
        // 完成删除
        int remove = this.baseMapper.deleteById(id);
        ResultVO resultVO = remove != 0 ? ResultVOUtil.success(null, "删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }

    @Override
    public PageVO getAllScarceRecord(SearchForm searchForm) {
        // 分页
        Page page = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        // 查询一级菜单
        QueryWrapper<SalesRecord> qwOne = new QueryWrapper<>();
        // 查所有订单编号去重
        // 查所有操作人员去重
        qwOne.select("Distinct record_order,record_salesperson,record_date,storemanage_id");
        // 分页查询条件
        if (searchForm.getKey().equals("record_salesperson")) {
            qwOne.eq(searchForm.getKey(), searchForm.getValue());
        } else {
            qwOne.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        }
        // 使用mapper查询
        Page<SalesRecord> oneList = this.baseMapper.selectPage(page, qwOne);
//        List<SalesRecord> oneList = baseMapper.selectList(qwOne);
        // 使用this查询，this = MenuManageServic
        // 查询二级菜单
        List<SalesRecord> twoList = baseMapper.selectList(null);
        // 创建集合储存最终封装数据
        List<OneSalesRecordList> finalList = new ArrayList<>();
        // 封装一级菜单
        for (int i = 0; i < oneList.getRecords().size(); i++) { // 遍历一级菜单集合
            // 取出值
            SalesRecord salesRecord = oneList.getRecords().get(i);
            // 放入值
            OneSalesRecordList oneSalesRecordList = new OneSalesRecordList();
            // 查售货员名字
            LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
            lqw.eq(User::getUserId, oneList.getRecords().get(i).getRecordSalesperson());
            User user = this.userMapper.selectOne(lqw);
            // 塞数据
            oneSalesRecordList.setRecordSalesperson(user.getUserName());
            oneSalesRecordList.setRecordOrder(oneList.getRecords().get(i).getRecordOrder());
            oneSalesRecordList.setRecordDate(oneList.getRecords().get(i).getRecordDate());
            oneSalesRecordList.setStoremanageId(oneList.getRecords().get(i).getStoremanageId());
            finalList.add(oneSalesRecordList);

            // 封装二级菜单
            List<TwoSalesRecordList> twoSalesRecordLists = new ArrayList<>();
            for (int m = 0; m < twoList.size(); m++) {
                // 取出值
                SalesRecord salesRecord1 = twoList.get(m);
                // 判断二级菜单后放入值
                if (salesRecord1.getRecordOrder().equals(salesRecord.getRecordOrder())) {
                    TwoSalesRecordList twoSalesRecordList = new TwoSalesRecordList();
                    BeanUtils.copyProperties(salesRecord1, twoSalesRecordList);
                    twoSalesRecordLists.add(twoSalesRecordList);
                }
            }
            // 二级菜单放入一级菜单
            oneSalesRecordList.setChildren(twoSalesRecordLists);
        }
        // 改分页输出的data
        page.setRecords(finalList);
        PageVO pageVO = PageVOUtil.success(page.getRecords(), "查询成功！", page.getTotal(), page.getCurrent(), page.getSize());
        return pageVO;
    }

    // 可视化

    // 格式化今年的数据
    @Override
    public ResultVO getFormatYear(Integer id) {
        // 最终输出结果
        List<ShowMonthData> showMonthDataList = new ArrayList<>();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));

        for (int i = 1; i <= 12; i++) {
            // 当前对象
            ShowMonthData showMonthData = new ShowMonthData();
            // 查找当月总销售额
            QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
            qw.lambda().eq(SalesRecord::getRecordSalesperson, id);
            if (i >= 10) {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-" + i);
            } else {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-0" + i);
            }
            List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
            // 算当月总销售额
            Integer sumPrice = 0;
            for (int j = 0; j < salesRecordList.size(); j++) {
                sumPrice = sumPrice + salesRecordList.get(j).getRecordPrice().intValue();
            }
            showMonthData.setName(year + "-" + i);
            showMonthData.setValue(sumPrice);
            showMonthDataList.add(showMonthData);
        }
        ResultVO resultVO = ResultVOUtil.successVisualization(showMonthDataList, "查询成功！");
        return resultVO;
    }

    // 格式化一个月的每一天
    @Override
    public ResultVO getFormatDay(ShowDayDataParameter showDayDataParameter) {
        // 最终输出
        ShowDayData showDayData = new ShowDayData();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        // 得到当前月
        Integer month = showDayDataParameter.getId() + 1;
        // 拼模糊查询
        String nowStr = month < 10 ? year + "-0" + month : year + "-" + month;
        // 查当前月数据
        QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
        qw.lambda().like(SalesRecord::getRecordDate, nowStr);
        qw.lambda().eq(SalesRecord::getRecordSalesperson, showDayDataParameter.getUserId());
        qw.orderByAsc("record_id");
        List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
        // 遍历当月数据
        List nowDay = new ArrayList();
        List nowValue = new ArrayList();
        for (int i = 0; i < salesRecordList.size(); i++) {
            Date nowDate = salesRecordList.get(i).getRecordDate();
            Integer nowValueInt = salesRecordList.get(i).getRecordPrice().intValue();
            // 转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String nowDateStr = simpleDateFormat.format(nowDate);
            // 判断是否重复
            boolean flag = true;
            if (nowDay.size() > 0) {
                if (String.valueOf(nowDay.get(nowDay.size() - 1)).equals(nowDateStr)) {
                    // 计算当前天的销售额
                    Integer valueSum = (Integer) nowValue.get(nowDay.size() - 1) + nowValueInt;
                    nowValue.set(nowDay.size() - 1, valueSum);
                    flag = false;
                }
            }
            if (flag) {
                // 日期塞进列表里
                nowDay.add(nowDateStr);
                // 数据塞进列表里
                nowValue.add(nowValueInt);
            }
        }
        showDayData.setDay(nowDay);
        showDayData.setValue(nowValue);
        ResultVO resultVO = ResultVOUtil.success(showDayData, "查询成功！");
        return resultVO;
    }



    // 热销货物
    @Override
    public ResultVO getSellingGoods() {
        // 最终输出
        List<ShowMonthData> showMonthDataList = new ArrayList<>();
        // 查所有货物去重
        QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
        qw.select("Distinct record_name");
        List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
        // 遍历货物塞进对象
        for (int i = 0; i < salesRecordList.size(); i++) {
            ShowMonthData showMonthData = new ShowMonthData();
            // 放货物名到对象
            showMonthData.setName(salesRecordList.get(i).getRecordName());
            // 查该货物有几个
            LambdaQueryWrapper<SalesRecord> lqw = new LambdaQueryWrapper<>();
            lqw.eq(SalesRecord::getRecordName, salesRecordList.get(i).getRecordName());
            int goodNum = this.baseMapper.selectCount(lqw);
            showMonthData.setValue(goodNum);
            // 塞进最终输出
            showMonthDataList.add(showMonthData);
        }
        // 排序算法
        for (int i = 0; i < showMonthDataList.size() - 1; i++) {
            for (int j = 0; j < showMonthDataList.size() - 1 - i; j++) {
                ShowMonthData temp;
                if (showMonthDataList.get(j).getValue() < showMonthDataList.get(j + 1).getValue()) {
                    temp = showMonthDataList.get(j);
                    showMonthDataList.set(j, showMonthDataList.get(j + 1));
                    showMonthDataList.set(j + 1, temp);
                }
            }
        }
        ResultVO resultVO = ResultVOUtil.success(showMonthDataList.subList(0, 6), "查询成功");
        return resultVO;
    }

    // 新增销售趋势
    @Override
    public ResultVO getSaleTrend() {
        // 最终输出结果
        List finalList = new ArrayList<>();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        for (int i = 1; i <= 12; i++) {
            // 里面的数组
            List insideList = new ArrayList();
            // 查找当月总销售额
            QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
            if (i >= 10) {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-" + i);
            } else {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-0" + i);
            }
            List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
            // 算当月总销售额
            Integer sumPrice = 0;
            for (int j = 0; j < salesRecordList.size(); j++) {
                sumPrice = sumPrice + salesRecordList.get(j).getRecordPrice().intValue();
            }
            insideList.add(year + "-" + i);
            insideList.add(sumPrice);
            finalList.add(insideList);
        }
        ResultVO resultVO = ResultVOUtil.success(finalList, "查询成功");
        return resultVO;
    }

    // 今年总销售额
    @Override
    public ResultVO getNowtotalSales() {
        // 总销售额
        long totalSales = 0;
        // 查全部销售额
        List<SalesRecord> salesRecordList = this.baseMapper.selectList(null);
        for (int i = 0; i < salesRecordList.size(); i++) {
            totalSales = totalSales + salesRecordList.get(i).getRecordPrice().intValue();
        }
        ResultVO resultVO = ResultVOUtil.success(totalSales, "查询成功");
        return resultVO;
    }

    //年销售总额统计
    @Override
    public ResultVO getNowAlltotalSales() {
        // 最终输出
        ShowDayData showDayData = new ShowDayData();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        // 查当前年数据
        QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
        qw.lambda().like(SalesRecord::getRecordDate, year);
        qw.orderByAsc("record_id");
        List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
        // 遍历当月数据
        List nowDay = new ArrayList();
        List nowValue = new ArrayList();
        for (int i = 0; i < salesRecordList.size(); i++) {
            Date nowDate = salesRecordList.get(i).getRecordDate();
            Integer nowValueInt = salesRecordList.get(i).getRecordPrice().intValue();
            // 转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String nowDateStr = simpleDateFormat.format(nowDate);
            // 判断是否重复
            boolean flag = true;
            if (nowDay.size() > 0) {
                if (String.valueOf(nowDay.get(nowDay.size() - 1)).equals(nowDateStr)) {
                    // 计算当前月的销售额
                    Integer valueSum = (Integer) nowValue.get(nowDay.size() - 1) + nowValueInt;
                    nowValue.set(nowDay.size() - 1, valueSum);
                    flag = false;
                }
            }
            if (flag) {
                // 日期塞进列表里
                nowDay.add(nowDateStr);
                // 数据塞进列表里
                nowValue.add(nowValueInt);
            }
        }
        showDayData.setDay(nowDay);
        showDayData.setValue(nowValue);
        ResultVO resultVO = ResultVOUtil.success(showDayData, "查询成功！");
        return resultVO;
    }

    // 上月各店铺销售单数
    @Override
    public ResultVO getSalesOrders() {
        // 获取上个月的 yyyy-MM 字符串
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1); // 回退到上个月
        String yearMonth = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());

        // 查询所有销售记录，过滤条件：record_date like 'yyyy-MM%'
        LambdaQueryWrapper<SalesRecord> salesWrapper = new LambdaQueryWrapper<>();
        salesWrapper.like(SalesRecord::getRecordDate, yearMonth);
        List<SalesRecord> salesRecords = this.baseMapper.selectList(salesWrapper);

        // 获取所有店铺信息（用于匹配店铺名称）
        List<StoreStoremanage> storeList = this.storeStoremanageMapper.selectList(null);
        Map<Long, String> storeIdToNameMap = storeList.stream()
                .collect(Collectors.toMap(StoreStoremanage::getStoremanageId, StoreStoremanage::getStoremanageName));

        // 统计每个店铺的销量
        Map<Long, Integer> storeIdToSalesNumMap = new HashMap<>();
        for (SalesRecord record : salesRecords) {
            Long storeId = record.getStoremanageId();
            Integer num = record.getRecordNum() != null ? record.getRecordNum().intValue() : 0;
            storeIdToSalesNumMap.merge(storeId, num, Integer::sum); // 累加销量
        }

        // 组装返回结构
        List<ShowMonthData> resultList = new ArrayList<>();
        for (Map.Entry<Long, Integer> entry : storeIdToSalesNumMap.entrySet()) {
            Long storeId = entry.getKey();
            Integer totalNum = entry.getValue();

            ShowMonthData data = new ShowMonthData();
            data.setName(storeIdToNameMap.getOrDefault(storeId, "未知店铺"));
            data.setValue(totalNum);
            resultList.add(data);
        }

        return ResultVOUtil.success(resultList, "查询成功");
    }

    // 上月各店铺销售额
    @Override
    public ResultVO getStoreOrders() {
        // 获取上个月的 yyyy-MM 格式字符串
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String lastMonthStr = new SimpleDateFormat("yyyy-MM").format(calendar.getTime());

        // 查询上个月所有销售记录
        LambdaQueryWrapper<SalesRecord> salesQuery = new LambdaQueryWrapper<>();
        salesQuery.like(SalesRecord::getRecordDate, lastMonthStr);
        List<SalesRecord> salesRecords = this.baseMapper.selectList(salesQuery);

        // 获取所有店铺，用于匹配店铺名称
        List<StoreStoremanage> storeList = this.storeStoremanageMapper.selectList(null);
        Map<Long, String> storeIdToNameMap = storeList.stream()
                .collect(Collectors.toMap(StoreStoremanage::getStoremanageId, StoreStoremanage::getStoremanageName));

        // 汇总每个店铺的收入
        Map<Long, BigDecimal> storeIncomeMap = new HashMap<>();
        for (SalesRecord record : salesRecords) {
            Long storeId = record.getStoremanageId();
            BigDecimal price = record.getRecordPrice() != null ? record.getRecordPrice() : BigDecimal.ZERO;
            storeIncomeMap.merge(storeId, price, BigDecimal::add);
        }

        // 组装返回结果
        List<ShowMonthData> showMonthDataList = new ArrayList<>();
        for (Map.Entry<Long, BigDecimal> entry : storeIncomeMap.entrySet()) {
            Long storeId = entry.getKey();
            BigDecimal totalIncome = entry.getValue();

            ShowMonthData data = new ShowMonthData();
            data.setName(storeIdToNameMap.getOrDefault(storeId, "未知店铺"));
            data.setValue(totalIncome.intValue()); // 如果你希望显示小数，可改成 double 或 BigDecimal
            showMonthDataList.add(data);
        }

        return ResultVOUtil.success(showMonthDataList, "查询成功");
    }


    // 获取员工
    @Override
    public ResultVO getObtainEmployees() {
        // 最终输出
        List<ShowObtainEmployeesOne> showObtainEmployeesOneList = new ArrayList<>();
        // 获取店铺
        List<StoreStoremanage> storeStoremanageList = this.storeStoremanageMapper.selectList(null);
        for (int i = 0; i < storeStoremanageList.size(); i++) {
            // 一级菜单塞数据
            ShowObtainEmployeesOne showObtainEmployeesOne = new ShowObtainEmployeesOne();
            showObtainEmployeesOne.setStoremanageId(storeStoremanageList.get(i).getStoremanageId());
            showObtainEmployeesOne.setStoremanageName(storeStoremanageList.get(i).getStoremanageName());
            // 查店铺里的销售员
            QueryWrapper<User> qw = new QueryWrapper<>();
            qw.lambda().eq(User::getUserWorkstore, storeStoremanageList.get(i).getStoremanageId());
            List<User> userList = this.userMapper.selectList(qw);
            // 往二级菜单塞数据
            List<ShowObtainEmployeesTwo> showObtainEmployeesTwoList = new ArrayList<>();
            for (int j = 0; j < userList.size(); j++) {
                ShowObtainEmployeesTwo showObtainEmployeesTwo = new ShowObtainEmployeesTwo();
                showObtainEmployeesTwo.setUserId(userList.get(j).getUserId());
                showObtainEmployeesTwo.setUserName(userList.get(j).getUserName());
                showObtainEmployeesTwoList.add(showObtainEmployeesTwo);
            }
            // 塞二级到一级
            showObtainEmployeesOne.setChildren(showObtainEmployeesTwoList);
            // 塞数据到数组里
            showObtainEmployeesOneList.add(showObtainEmployeesOne);
        }
        ResultVO resultVO = ResultVOUtil.success(showObtainEmployeesOneList, "查询成功");
        return resultVO;
    }

    // 上月员工销售数据
    @Override
    public ResultVO getEmployeeData() {
        // 最终输出
        List<UserList> userLists = new ArrayList<>();
        // 查已分配店铺的用户
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.lambda().isNotNull(User::getUserWorkstore);
        List<User> userList = this.userMapper.selectList(qw);
        // 找该用户的销售数据
        for (int i = 0; i < userList.size(); i++) {
            UserList userListObj = new UserList();
            userListObj.setName(userList.get(i).getUserName());
            // 销售额
            QueryWrapper<SalesRecord> qwSR = new QueryWrapper<>();
            qwSR.lambda().eq(SalesRecord::getRecordSalesperson, userList.get(i).getUserId());
            List<SalesRecord> salesRecordList = this.baseMapper.selectList(qwSR);
            Integer sum = 0;
            for (int j = 0; j < salesRecordList.size(); j++) {
                sum = sum + salesRecordList.get(j).getRecordPrice().intValue();
            }
            userListObj.setValue(sum);
            userLists.add(userListObj);
        }
        ResultVO resultVO = ResultVOUtil.success(userLists, "查询成功");
        return resultVO;
    }

    // 员工当年销售额
    @Override
    public ResultVO getRevenueEmployees(Integer id) {
        // 最终输出
        ShowDayData showDayData = new ShowDayData();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        // 查当前年数据
        QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
        qw.lambda().like(SalesRecord::getRecordDate, year);
        qw.orderByAsc("record_id");
        qw.lambda().eq(SalesRecord::getRecordSalesperson,id);
        List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
        // 遍历当月数据
        List nowDay = new ArrayList();
        List nowValue = new ArrayList();
        for (int i = 0; i < salesRecordList.size(); i++) {
            Date nowDate = salesRecordList.get(i).getRecordDate();
            Integer nowValueInt = salesRecordList.get(i).getRecordPrice().intValue();
            // 转换日期格式
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
            String nowDateStr = simpleDateFormat.format(nowDate);
            // 判断是否重复
            boolean flag = true;
            if (nowDay.size() > 0) {
                if (String.valueOf(nowDay.get(nowDay.size() - 1)).equals(nowDateStr)) {
                    // 计算当前月的销售额
                    Integer valueSum = (Integer) nowValue.get(nowDay.size() - 1) + nowValueInt;
                    nowValue.set(nowDay.size() - 1, valueSum);
                    flag = false;
                }
            }
            if (flag) {
                // 日期塞进列表里
                nowDay.add(nowDateStr);
                // 数据塞进列表里
                nowValue.add(nowValueInt);
            }
        }
        showDayData.setDay(nowDay);
        showDayData.setValue(nowValue);
        ResultVO resultVO = ResultVOUtil.success(showDayData, "查询成功！");
        return resultVO;
    }

    // 员工新增销售额趋势
    @Override
    public ResultVO getSalesTrends(Integer id) {
        // 最终输出结果
        List finalList = new ArrayList<>();
        // 得到当前年
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        for (int i = 1; i <= 12; i++) {
            // 里面的数组
            List insideList = new ArrayList();
            QueryWrapper<SalesRecord> qw = new QueryWrapper<>();
            qw.lambda().eq(SalesRecord::getRecordSalesperson,id);
            if (i >= 10) {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-" + i);
            } else {
                qw.lambda().like(SalesRecord::getRecordDate, year + "-0" + i);
            }
            List<SalesRecord> salesRecordList = this.baseMapper.selectList(qw);
            Integer sumPrice = 0;
            for (int j = 0; j < salesRecordList.size(); j++) {
                sumPrice = sumPrice + salesRecordList.get(j).getRecordPrice().intValue();
            }
            insideList.add(year + "-" + i);
            insideList.add(sumPrice);
            finalList.add(insideList);
        }
        ResultVO resultVO = ResultVOUtil.success(finalList, "查询成功");
        return resultVO;
    }

    @Override
    public ResultVO getSalesRevenue(Integer id) {
        return null;
    }
}
