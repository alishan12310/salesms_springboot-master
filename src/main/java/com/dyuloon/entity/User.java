package com.dyuloon.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author dyuloon
 * @since 2023-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户头像路径
     */
    private String userImg;

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

    /**
     * 工作店铺
     */
    private String userWorkstore;

    /**
     * 职务
     */
    private String userAppointment;

    /**
     * 状态（0:在职,1:离职）
     */
    private Integer userState;

    /**
     * 用户身份：0（管理员）1（销售员）
     */
    private Integer userIdentity;

    /**
     * 创建时间
     */
    @JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date userCreatedate;
}
