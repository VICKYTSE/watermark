package com.cumt.watermark.dao;

import com.cumt.watermark.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderADao {
    List<Order> getTenRecord();
    int getAllCount();
    int getCountByStartHour(@Param("h") int h);
    int getCountByEndHour(@Param("h") int h);
    int getCountByDuration(@Param("k") int k);
    int getCountByHourAndDuration(@Param("k")int k, @Param("h") int h);
}
