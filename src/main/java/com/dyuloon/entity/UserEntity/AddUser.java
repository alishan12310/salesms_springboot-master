package com.dyuloon.entity.UserEntity;

import lombok.Data;

@Data
public class AddUser {
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
