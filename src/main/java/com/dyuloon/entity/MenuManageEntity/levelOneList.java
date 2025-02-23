package com.dyuloon.entity.MenuManageEntity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class levelOneList {
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

    /**
     * 二级列表（一个一级列表有多个二级列表）
     */
    private List<levelTwoList> children = new ArrayList<>();

}
