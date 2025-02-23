package com.dyuloon.entity.SalesRecordEntity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShowDayData {
    private List day = new ArrayList<>();
    private List value = new ArrayList<>();
}
