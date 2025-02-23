package com.dyuloon.from;

import lombok.Data;

@Data
public class SearchForm {
    private String key;
    private String value;
    private Integer flag;
    private Integer userIdentity;
    private Integer pageNum;
    private Integer pageSize;
}
