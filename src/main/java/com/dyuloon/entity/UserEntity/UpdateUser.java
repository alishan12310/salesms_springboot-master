package com.dyuloon.entity.UserEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UpdateUser {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 用户电话（账号）
     */
    private Long userTel;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String userGender;

    /**
     * 电子邮箱
     */
    private String userEmail;

    /**
     * 备注
     */
    private String userNotes;
}
