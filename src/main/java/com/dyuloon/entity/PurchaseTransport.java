package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class PurchaseTransport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 运输id
     */
    @TableId(value = "transport_id", type = IdType.AUTO)
    private Long transportId;

    /**
     * 运输方式
     */
    private String transportMode;

    /**
     * 司机名字
     */
    private String transportDriver;

    /**
     * 司机电话
     */
    private String transportDrivertel;

    /**
     * 车牌号
     */
    private String transportCarnumber;

    /**
     * 开始日期
     */
    private LocalDate transportStartdate;

    /**
     * 结束日期
     */
    private LocalDate transportEnddate;

    /**
     * 库房（id）
     */
    private String transportStoragehouse;

    /**
     * 订单编号
     */
    private String transportOrdernumber;

    /**
     * 运输状态
     */
    private String transportState;


}
