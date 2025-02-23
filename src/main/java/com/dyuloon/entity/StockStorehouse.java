package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Data
@ToString
  @EqualsAndHashCode(callSuper = false)
    public class StockStorehouse implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 库房id
     */
        @TableId(value = "storehouse_id", type = IdType.AUTO)
      private Long storehouseId;

      /**
     * 库房名
     */
      private String storehouseName;

      /**
     * 地址
     */
      private String storehouseAddress;

      /**
     * 备注
     */
      private String storehouseNotes;


}
