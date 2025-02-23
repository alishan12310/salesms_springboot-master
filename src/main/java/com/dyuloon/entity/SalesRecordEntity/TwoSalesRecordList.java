package com.dyuloon.entity.SalesRecordEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TwoSalesRecordList {
    /**
     * 售出记录id
     */
    private Long recordId;
    /**
     * 商品名字
     */
    private String recordName;

    /**
     * 售出数量
     */
    private Long recordNum;

    /**
     * 总价
     */
    private BigDecimal recordPrice;

}
