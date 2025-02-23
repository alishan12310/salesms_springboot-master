package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class StockUnit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 单位id
     */
    @TableId(value = "unit_id", type = IdType.AUTO)
    private Long unitId;

    /**
     * 单位名
     */
    private String unitName;

    /**
     * 单位备注
     */
    private String unitNotes;


}
