package com.dyuloon.entity.UserEntity;

import lombok.Data;

@Data
public class DelegateUser {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 工作店铺
     */
    private String userWorkstore;

    /**
     * 职务
     */
    private String userAppointment;
}
