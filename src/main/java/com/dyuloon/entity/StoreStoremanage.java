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
 * @since 2023-01-19
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class StoreStoremanage implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 店铺id
     */
        @TableId(value = "storemanage_id", type = IdType.AUTO)
      private Long storemanageId;

      /**
     * 店名
     */
      private String storemanageName;

      /**
     * 主营
     */
      private String storemanageContent;

      /**
     * 地址
     */
      private String storemanageAddress;

      /**
     * 备注
     */
      private String storemanageNotes;


}
