package com.cumt.watermark.service;

import com.cumt.watermark.entity.Order;

import java.util.List;

public interface OrderBService {
    List<Order> getTenRecord();
    List<Integer> getStartHoursCount();
    List<Integer> getEndHoursCount();
    int getAllCount();
    List<Integer> getDurationCount();
    List<List<Integer>> getDurationCountDivByHour();
}
