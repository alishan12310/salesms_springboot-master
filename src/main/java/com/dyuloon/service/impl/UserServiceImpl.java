package com.dyuloon.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dyuloon.controller.StockStorehouseController;
import com.dyuloon.entity.StockStorehouse;
import com.dyuloon.entity.StoreStoremanage;
import com.dyuloon.entity.User;
import com.dyuloon.entity.UserEntity.AddUser;
import com.dyuloon.entity.UserEntity.DelegateUser;
import com.dyuloon.entity.UserEntity.DepartUser;
import com.dyuloon.entity.UserEntity.UpdateUser;
import com.dyuloon.from.RuleFrom;
import com.dyuloon.from.SearchForm;
import com.dyuloon.mapper.StockStorehouseMapper;
import com.dyuloon.mapper.StoreStoremanageMapper;
import com.dyuloon.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dyuloon.service.UserService;
import com.dyuloon.util.PageVOUtil;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.apache.ibatis.annotations.Update;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-15
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private StoreStoremanageMapper storeStoremanageMapper;

    @Override
    public ResultVO login(RuleFrom ruleFrom) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_tel", ruleFrom.getUsername());
        queryWrapper.eq("user_state", 0);
        User user = this.userMapper.selectOne(queryWrapper);
        ResultVO resultVO = null;
        if (user == null || !user.getUserPassword().equals(ruleFrom.getPassword()) || !user.getUserIdentity().equals(ruleFrom.getFlag())) {
            resultVO = ResultVOUtil.fail("用户名或密码错误");
        } else {
            resultVO = ResultVOUtil.success(user, "登录成功");
        }
        return resultVO;
    }

    @Override
    public PageVO queryEmployee(SearchForm searchForm) {
//        System.out.println(searchForm);
        Page<User> userPage = new Page<>(searchForm.getPageNum(),searchForm.getPageSize());
        Page<User> resultPage = null;
        PageVO pageVO = null;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_identity", searchForm.getUserIdentity());
        if(!searchForm.getValue().equals("")){
            queryWrapper.like(searchForm.getKey(),searchForm.getValue());
        }
        if(searchForm.getFlag() == 1){
            resultPage = this.userMapper.selectPage(userPage,queryWrapper.isNull("user_workstore"));
        }else if(searchForm.getFlag() == 2) {
            resultPage = this.userMapper.selectPage(userPage,queryWrapper.orderByDesc("user_id").isNotNull("user_workstore"));
        }else if(searchForm.getFlag() == 0) {
            resultPage = this.userMapper.selectPage(userPage,queryWrapper.orderByDesc("user_id"));
        }
        List<User> userList = resultPage.getRecords();
        // 把店铺id换成店铺汉字
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserWorkstore() != null) {
                StoreStoremanage storeStoremanage = this.storeStoremanageMapper.selectById(userList.get(i).getUserWorkstore());
                userList.get(i).setUserWorkstore(storeStoremanage.getStoremanageName());
            }
        }
        resultPage.setRecords(userList);
        pageVO = PageVOUtil.success(resultPage.getRecords(),"查询成功！",resultPage.getTotal(),resultPage.getCurrent(),resultPage.getSize());
        return pageVO;
    }

    // 查找用户信息
    @Override
    public ResultVO queryPersonalInformation(Integer id) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserId,id);
        User user = this.baseMapper.selectOne(lqw);
        ResultVO resultVO = ResultVOUtil.success(user,"查询成功！");
        return resultVO;
    }

    // 查所有人员
    @Override
    public PageVO queryAllMan(SearchForm searchForm) {
        Page storePage = new Page(searchForm.getPageNum(), searchForm.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(searchForm.getValue()), searchForm.getKey(), searchForm.getValue());
        queryWrapper.orderByDesc("user_id");
        baseMapper.selectPage(storePage, queryWrapper);
        List<User> list = storePage.getRecords();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserWorkstore() != null){
                StoreStoremanage storeStoremanage = this.storeStoremanageMapper.selectById(list.get(i).getUserWorkstore());
                list.get(i).setUserWorkstore(storeStoremanage.getStoremanageName());
            }
        }
        storePage.setRecords(list);
        PageVO pageVO = PageVOUtil.success(storePage.getRecords(), "查询成功！", storePage.getTotal(), storePage.getCurrent(), storePage.getSize());
        return pageVO;
    }
    // 添加员工
    @Override
    public ResultVO adduser(AddUser addUser) {
        User user = new User();
        user.setUserState(0);
        user.setUserIdentity(1);
        BeanUtils.copyProperties(addUser,user);
        int save = this.userMapper.insert(user);
        ResultVO resultVO = save == 1 ? ResultVOUtil.success(null,"添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }

    // 修改员工
    @Override
    public ResultVO updateEmployee(UpdateUser updateUser) {
        User user = new User();
        BeanUtils.copyProperties(updateUser,user);
        int update = this.baseMapper.updateById(user);
        ResultVO resultVO = update != 0 ? ResultVOUtil.success(null,"更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    // 员工状态
    @Override
    public ResultVO depart(Integer id) {
        User user = this.getById(id);
        if (user == null) {
            return ResultVOUtil.fail("员工不存在！");
        }

        Integer currentState = user.getUserState();
        user.setUserState(currentState == 0 ? 1 : 0);

        boolean updated = this.updateById(user);
        if (updated) {
            String msg = user.getUserState() == 0 ? "已设为在职" : "已设为离职";
            return ResultVOUtil.success(null, msg);
        } else {
            return ResultVOUtil.fail("更新失败！");
        }
    }


    @Override
    public ResultVO delegateEmployee(DelegateUser delegateUser) {
        User user = this.baseMapper.selectById(delegateUser.getUserId());
        user.setUserWorkstore(delegateUser.getUserWorkstore());
        user.setUserAppointment(delegateUser.getUserAppointment());
        int update = this.baseMapper.updateById(user);
        ResultVO resultVO = update != 0 ? ResultVOUtil.success(null,"委派成功！") : ResultVOUtil.fail("委派失败！");
        return resultVO;
    }

    // 根据店铺id查员工
    @Override
    public ResultVO queryAllManByStorehouse(Integer id) {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserWorkstore,id);
        List<User> userList = this.baseMapper.selectList(lqw);
        ResultVO resultVO = ResultVOUtil.success(userList,"查询成功！");
        return resultVO;
    }

    @Override
    public ResultVO updateAllMan(User user) {
        int update = this.baseMapper.updateById(user);
        ResultVO resultVO = update != 0 ? ResultVOUtil.success(null,"更新成功！") : ResultVOUtil.fail("更新失败！");
        return resultVO;
    }

    @Override
    public ResultVO deleteAllMan(Integer id) {
        int remove = this.baseMapper.deleteById(id);
        ResultVO resultVO = remove == 1 ? ResultVOUtil.success(null,"删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }

    // 人员管理：添加人员
    @Override
    public ResultVO addAllMan(User user) {
        user.setUserState(0);
        int save = this.userMapper.insert(user);
        ResultVO resultVO = save == 1 ? ResultVOUtil.success(null,"添加成功！") : ResultVOUtil.fail("添加失败！");
        return resultVO;
    }

}
