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
    public class StockCategory implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 类别id
     */
        @TableId(value = "category_id", type = IdType.AUTO)
      private Long categoryId;

      /**
     * 类名
     */
      private String categoryName;

      /**
     * 备注
     */
      private String categoryNotes;


}
