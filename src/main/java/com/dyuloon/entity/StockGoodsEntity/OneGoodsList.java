package com.dyuloon.entity.StockGoodsEntity;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OneGoodsList {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 类别
     */
    private String goodsName;

    /**
     * 二级列表（一个一级列表有多个二级列表）
     */
    private List<TwoGoodsList> children = new ArrayList<>();
}
