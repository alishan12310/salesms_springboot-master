package com.dyuloon.entity.MenuManageEntity;

import lombok.Data;

@Data
public class OnlyTwoList {
    private Long menuId;

    /**
     * 名称
     */
    private String menuTitle;


    /**
     * 路由路径
     */
    private String menuIndex;

    /**
     * 完整路径
     */
    private String menuPath;

}
