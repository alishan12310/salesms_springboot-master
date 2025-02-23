package com.dyuloon.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageVO<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer total;
    private Integer code;
    private List<T> data;
    private String msg;
}
