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
    public class StockGoods implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 商品id
     */
        @TableId(value = "goods_id", type = IdType.AUTO)
      private Long goodsId;

      /**
     * 类别
     */
      private String goodsCategory;

      /**
     * 商品名
     */
      private String goodsName;

      /**
     * 价格
     */
      private String goodsPrice;

      /**
     * 数量
     */
      private Integer goodsQuantity;

      /**
     * 单位
     */
      private String goodsUnit;

      /**
     * 存储库房
     */
      private String goodsStorehouse;



}
