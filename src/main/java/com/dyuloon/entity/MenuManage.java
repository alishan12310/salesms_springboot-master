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
 * @since 2023-04-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MenuManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单id
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 名称
     */
    private String menuTitle;

    /**
     * 图标
     */
    private String menuIcon;

    /**
     * 路由路径
     */
    private String menuIndex;

    /**
     * 完整路径
     */
    private String menuPath;

    /**
     * 级别（0：顶级；顶级id：二级）
     */
    private Long menuLevel;

    /**
     * 排序
     */
    private Long menuSort;

    /**
     * 显示状态（0隐藏；1显示）
     */
    private Long menuShow;

    /**
     * 权限管理（0管理员；1销售员）
     */
    private Long menuRights;


}
