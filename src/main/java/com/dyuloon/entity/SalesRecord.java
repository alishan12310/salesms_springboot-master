package com.dyuloon.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SalesRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 售出记录id
     */
    @TableId(value = "record_id", type = IdType.AUTO)
    private Long recordId;

    /**
     * 订单id
     */
    private String recordOrder;

    /**
     * 商品id
     */
    private Long recordGoods;

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

    /**
     * 销售人员id
     */
    private Long recordSalesperson;

    /**
     * 订单日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordDate;

    private Long storemanageId;
}
