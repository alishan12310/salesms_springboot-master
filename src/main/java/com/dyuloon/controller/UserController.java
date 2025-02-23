package com.dyuloon.controller;


import com.dyuloon.entity.User;
import com.dyuloon.entity.UserEntity.AddUser;
import com.dyuloon.entity.UserEntity.DelegateUser;
import com.dyuloon.entity.UserEntity.DepartUser;
import com.dyuloon.entity.UserEntity.UpdateUser;
import com.dyuloon.from.RuleFrom;
import com.dyuloon.from.SearchForm;
import com.dyuloon.service.UserService;
import com.dyuloon.util.ResultVOUtil;
import com.dyuloon.vo.PageVO;
import com.dyuloon.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-15
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 登录
    @GetMapping("/login")
    public ResultVO login(RuleFrom ruleFrom) {
        ResultVO resultVO = this.userService.login(ruleFrom);
        return resultVO;
    }

    // 添加员工
    @PostMapping("/saveEmployee")
    public ResultVO adduser(@RequestBody AddUser addUser) {
//        System.out.println("111111111"+user);
        ResultVO resultVO = this.userService.adduser(addUser);
        return resultVO;
    }

    // 查询员工
    @GetMapping("queryEmployee")
    public ResultVO queryEmployee(SearchForm searchForm){
        PageVO pageVO = this.userService.queryEmployee(searchForm);
        return ResultVOUtil.success(pageVO,"查询成功！");
    }

    // 修改员工
    @PutMapping("/updateEmployee")
    public ResultVO updateEmployee(@RequestBody UpdateUser updateUser){
        ResultVO resultVO = this.userService.updateEmployee(updateUser);
        return resultVO;
    }
    // 委派员工
    @PutMapping("/delegateEmployee")
    public ResultVO delegateEmployee(@RequestBody DelegateUser delegateUser){
        ResultVO resultVO = this.userService.delegateEmployee(delegateUser);
        return resultVO;
    }

    // 删除员工
    @DeleteMapping("/deleteEmployee/{id}")
    public ResultVO deleteEmployee(@PathVariable Integer id){
        boolean remove = this.userService.removeById(id);
        ResultVO resultVO = remove ? ResultVOUtil.success(null,"删除成功！") : ResultVOUtil.fail("删除失败！");
        return resultVO;
    }

    // 员工离职
    @PutMapping("/depart/{id}")
    public ResultVO depart(@PathVariable Integer id){
        ResultVO resultVO = this.userService.depart(id);
        return resultVO;
    }


    // 查询个人信息
    @GetMapping("{id}")
    public ResultVO queryPersonalInformation(@PathVariable Integer id){
        ResultVO resultVO = this.userService.queryPersonalInformation(id);
        return resultVO;
    }

    // 修改个人信息
    @PutMapping("/updatePersonalInformation")
    public ResultVO updatePersonalInformation(@RequestBody User user){
        boolean update = this.userService.updateById(user);
        ResultVO resultVO = null;
        if(!update){
            resultVO = ResultVOUtil.fail("更新失败！");
        }else
            resultVO = ResultVOUtil.success(null,"更新成功！");
        return resultVO;
    }

    // 查所有人员信息
    @GetMapping("queryAllMan")
    public PageVO queryAllMan(SearchForm searchForm){
        PageVO pageVO = this.userService.queryAllMan(searchForm);
        return pageVO;
    }

    // 根据店铺id查所有人员信息
    @GetMapping("queryAllManByStorehouse/{id}")
    public ResultVO queryAllManByStorehouse(@PathVariable Integer id){
        ResultVO resultVO = this.userService.queryAllManByStorehouse(id);
        return resultVO;
    }

    // 不分页查所有人员信息
    @GetMapping("queryAllPeople")
    public ResultVO queryAllPeople(){
        List<User> list = this.userService.list();
        ResultVO resultVO = list != null ? ResultVOUtil.success(list,"查询成功") : ResultVOUtil.error("查询失败");
        return resultVO;
    }

    // 改所有人员信息
    @PutMapping("updateAllMan")
    public ResultVO updateAllMan(@RequestBody User user){
        ResultVO resultVO = this.userService.updateAllMan(user);
        return resultVO;
    }

    // 新增所有人员信息
    @PostMapping("addAllMan")
    public ResultVO addAllMan(@RequestBody User user){
        ResultVO resultVO = this.userService.addAllMan(user);
        return resultVO;
    }

    // 删除所有人员信息
    @DeleteMapping("deleteAllMan/{id}")
    public ResultVO deleteAllMan(@PathVariable Integer id){
        ResultVO resultVO = this.userService.deleteAllMan(id);
        return resultVO;
    }
}

