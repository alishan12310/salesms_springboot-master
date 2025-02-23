package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    @TableId(value = "order_id", type = IdType.AUTO)
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 厂家
     */
    private String orderCorporation;

    /**
     * 采购商品名
     */
    private String orderGoods;

    /**
     * 商品类别
     */
    private String orderCategory;

    /**
     * 采购数量
     */
    private String orderNum;

    /**
     * 单位
     */
    private String orderUnit;

    /**
     * 批发单价
     */
    private String orderPrice;

    /**
     * 0（未开始）1（开始）2（完成）
     */
    private Integer orderState;

    /**
     * 订单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Timestamp orderDate;


}
