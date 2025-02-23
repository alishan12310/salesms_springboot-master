package com.dyuloon.service;

import com.dyuloon.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dyuloon.entity.UserEntity.AddUser;
import com.dyuloon.entity.UserEntity.DelegateUser;
import com.dyuloon.entity.UserEntity.DepartUser;
import com.dyuloon.entity.UserEntity.UpdateUser;
import com.dyuloon.from.RuleFrom;
import com.dyuloon.from.SearchForm;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-15
 */
public interface UserService extends IService<User> {
    public ResultVO login(RuleFrom ruleFrom);
    public PageVO queryEmployee(SearchForm searchForm);

    ResultVO queryPersonalInformation(Integer id);

    // 查所有人员
    PageVO queryAllMan(SearchForm searchForm);
    // 改
    ResultVO updateAllMan(User user);

    ResultVO addAllMan(User user);

    ResultVO deleteAllMan(Integer id);

    ResultVO adduser(AddUser addUser);

    ResultVO updateEmployee(UpdateUser updateUser);

    ResultVO depart(Integer id);

    ResultVO delegateEmployee(DelegateUser delegateUser);

    ResultVO queryAllManByStorehouse(Integer id);
}
