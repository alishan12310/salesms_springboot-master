package com.dyuloon.entity.SalesRecordEntity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShowObtainEmployeesOne {

    /**
     * 店铺id
     */
    private Long storemanageId;

    /**
     * 店名
     */
    private String storemanageName;

    /**
     * 二级列表（一个一级列表有多个二级列表）
     */
    private List<ShowObtainEmployeesTwo> children = new ArrayList<>();
}
