package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dyuloon.entity.MenuManage;
import com.dyuloon.entity.MenuManageEntity.OnlyTwoList;
import com.dyuloon.entity.MenuManageEntity.levelOneList;
import com.dyuloon.entity.MenuManageEntity.levelTwoList;
import com.dyuloon.entity.MenuManageEntity.OnlyOneList;
import com.dyuloon.mapper.MenuManageMapper;
import com.dyuloon.service.MenuManageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-04-07
 */
@Service
public class MenuManageServiceImpl extends ServiceImpl<MenuManageMapper, MenuManage> implements MenuManageService {

    // 展示一二级菜单列表
    @Override
    public List<levelOneList> getAllMenuList(Integer id) {
        // 查询一级菜单
        LambdaQueryWrapper<MenuManage> lqwOne = new LambdaQueryWrapper<>();
        // 判断权限
        if(id == 1){
            lqwOne.eq(MenuManage::getMenuRights,id);
        }
        lqwOne.eq(MenuManage::getMenuLevel,0);
        // 使用mapper查询
        lqwOne.orderByAsc(MenuManage::getMenuSort);
        List<MenuManage> oneList = baseMapper.selectList(lqwOne);
        // 使用this查询，this = MenuManageServic
        // this.list(lqwOne);

        // 查询二级菜单
        LambdaQueryWrapper<MenuManage> lqwTwo = new LambdaQueryWrapper<>();
        lqwTwo.ne(MenuManage::getMenuLevel,0);
        if(id == 1){
            lqwTwo.eq(MenuManage::getMenuRights,id);
        }
        List<MenuManage> twoList = baseMapper.selectList(lqwTwo);

        // 创建集合储存最终封装数据
        List<levelOneList> finalList = new ArrayList<>();

        // 封装一级菜单
        for (int i = 0; i < oneList.size(); i++) { // 遍历一级菜单集合
            // 取出值
            MenuManage menuManage = oneList.get(i);
            // 放入值
            levelOneList levelOneList = new levelOneList();
            BeanUtils.copyProperties(menuManage,levelOneList);
            finalList.add(levelOneList);

            // 封装二级菜单
            List<levelTwoList> finalTwoList = new ArrayList<>();
            for (int m = 0; m < twoList.size(); m++) {
                // 取出值
                MenuManage menuManage2 = twoList.get(m);
                // 判断二级菜单后放入值
                if(menuManage2.getMenuLevel().equals(menuManage.getMenuId())){
                    levelTwoList levelTwoList = new levelTwoList();
                    BeanUtils.copyProperties(menuManage2,levelTwoList);
                    finalTwoList.add(levelTwoList);
                }
            }
            // 二级菜单放入一级菜单
            levelOneList.setChildren(finalTwoList);
        }
        return finalList;
    }

    // 查全部一级菜单
    @Override
    public List<OnlyOneList> getAllOneMenuList() {
        LambdaQueryWrapper<MenuManage> lqw = new LambdaQueryWrapper<>();
        lqw.eq(MenuManage::getMenuLevel,0);
        // 使用mapper查询
        List<MenuManage> oneList = baseMapper.selectList(lqw);

        List<OnlyOneList> onlyOneList = new ArrayList<>();

        for (int i = 0; i < oneList.size(); i++) {
            OnlyOneList list = new OnlyOneList();
            list.setMenuId(oneList.get(i).getMenuId());
            list.setMenuTitle(oneList.get(i).getMenuTitle());
            onlyOneList.add(list);
        }
        return onlyOneList;
    }

    // 查全部二级菜单
    @Override
    public List<OnlyTwoList> getAllTwoMenuList(Integer id) {
        LambdaQueryWrapper<MenuManage> lqw = new LambdaQueryWrapper<>();
        lqw.ne(MenuManage::getMenuLevel,0);
        if(id == 1){
            lqw.eq(MenuManage::getMenuRights,id);
        }
        // 使用mapper查询
        List<MenuManage> twoList = baseMapper.selectList(lqw);
        List<OnlyTwoList> onlyTwoList = new ArrayList<>();
        for (int i = 0; i < twoList.size(); i++) {
            OnlyTwoList list = new OnlyTwoList();
            MenuManage menuManage = twoList.get(i);
            BeanUtils.copyProperties(menuManage,list);
            onlyTwoList.add(list);
        }
        return onlyTwoList;
    }

    // 新增
    @Override
    public ResultVO addMenuList(MenuManage menuManage) {
        int save = this.baseMapper.insert(menuManage);
        ResultVO resultVO = save == 1 ? ResultVOUtil.success(null,"添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }
    // 修改
    @Override
    public ResultVO updateMenuList(MenuManage menuManage) {
        int update = this.baseMapper.updateById(menuManage);
        ResultVO resultVO = update == 1 ? ResultVOUtil.success(null,"更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    // 删除
    @Override
    public ResultVO deleteMenuList(Integer id) {
        int remove = this.baseMapper.deleteById(id);
        ResultVO resultVO = remove == 1 ? ResultVOUtil.success(null,"删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }

}
