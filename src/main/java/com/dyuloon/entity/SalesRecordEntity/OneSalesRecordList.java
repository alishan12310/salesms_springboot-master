package com.dyuloon.entity.SalesRecordEntity;

import com.dyuloon.entity.StockGoodsEntity.TwoGoodsList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OneSalesRecordList {
    /**
     * 销售人员name
     */
    private String recordSalesperson;
    /**
     * 订单编号
     */
    private String recordOrder;

    private Long storemanageId;
    /**
     * 订单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordDate;
    /**
     * 二级列表（一个一级列表有多个二级列表）
     */
    private List<TwoSalesRecordList> children = new ArrayList<>();
}
