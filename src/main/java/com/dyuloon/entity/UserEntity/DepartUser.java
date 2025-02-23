package com.dyuloon.entity.UserEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DepartUser {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 状态（0:在职,1:离职）
     */
    private String userState;
}
