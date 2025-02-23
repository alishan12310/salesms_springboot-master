package com.dyuloon.service;

import com.dyuloon.entity.MenuManage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.entity.MenuManageEntity.OnlyTwoList;
import com.dyuloon.entity.MenuManageEntity.levelOneList;
import com.dyuloon.entity.MenuManageEntity.OnlyOneList;
import com.dyuloon.entity.MenuManageEntity.levelTwoList;
import com.dyuloon.vo.ResultVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-07
 */
public interface MenuManageService extends IService<MenuManage> {
    // 返回菜单列表
    List<levelOneList> getAllMenuList(Integer id);

    // 添加菜单列表
    ResultVO addMenuList(MenuManage menuManage);

    // 修改菜单列表
    ResultVO updateMenuList(MenuManage menuManage);

    // 删除菜单列表
    ResultVO deleteMenuList(Integer id);

    // 获取所有一级菜单列表
    List<OnlyOneList> getAllOneMenuList();

    // 获取二级列表
    List<OnlyTwoList> getAllTwoMenuList(Integer id);
}
