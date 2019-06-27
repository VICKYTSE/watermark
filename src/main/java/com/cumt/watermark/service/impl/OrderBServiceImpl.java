package com.cumt.watermark.service.impl;

import com.cumt.watermark.dao.OrderBDao;
import com.cumt.watermark.entity.Order;
import com.cumt.watermark.service.OrderBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderBServiceImpl implements OrderBService {

    @Autowired
    private OrderBDao obDao;

    public List<Order> getTenRecord() {
        return obDao.getTenRecord();
    }

    @Override
    public List<Integer> getStartHoursCount() {
        List<Integer> count = new ArrayList<>();
        for(int i = 1; i <= 24; i++){
            count.add(obDao.getCountByStartHour(i));
        }
        return count;
    }

    @Override
    public List<Integer> getEndHoursCount() {
        List<Integer> count = new ArrayList<>();
        for(int i = 1; i <= 24; i++){
            count.add(obDao.getCountByEndHour(i));
        }
        return count;
    }

    @Override
    public int getAllCount() {
        return obDao.getAllCount();
    }

    @Override
    public List<Integer> getDurationCount() {
        List<Integer> count = new ArrayList<>();
        count.add(obDao.getCountByDuration(1)); //15分钟内
        count.add(obDao.getCountByDuration(2)); //15分钟到30分钟
        count.add(obDao.getCountByDuration(3)+
                obDao.getCountByDuration(4));//30分钟到1小时内
        int c = 0;
        for(int i = 5; i <=8;i++){
            c += obDao.getCountByDuration(i);
        }
        count.add(c);//1-2小时内
        int total = 0;
        for(Integer element: count){
            total += element.intValue();
        }
        count.add(obDao.getAllCount()-total);
        return count;
    }

    @Override
    public List<List<Integer>> getDurationCountDivByHour() {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> c1 = new ArrayList<>();
        for(int i = 1; i<= 24 ; i++){
            c1.add(obDao.getCountByHourAndDuration(1,i));
        }
        list.add(c1);   //15分钟

        List<Integer> c2 = new ArrayList<>();
        for(int i = 1; i<=24;i++){
            c2.add(obDao.getCountByHourAndDuration(2,i));
        }
        list.add(c2);  //15-30分钟

        List<Integer> c3 = new ArrayList<>();
        for(int i = 1; i<=24;i++){
            c3.add(obDao.getCountByHourAndDuration(3,i)+obDao.getCountByHourAndDuration(4,i));
        }
        list.add(c3);

        List<Integer> c4 = new ArrayList<>();
        for(int i = 1; i <= 24; i++){
            int c = 0;
            for(int h = 5; h <=8; h++){
                c += obDao.getCountByHourAndDuration(h,i);
            }
            c4.add(c);
        }
        list.add(c4);

        List<Integer> c5 = new ArrayList<>();
        for(int i = 1; i <= 24; i++){
            int c = 0;
            for(int h = 9; h <=11; h++){
                c += obDao.getCountByHourAndDuration(h,i);
            }
            c5.add(c);
        }
        list.add(c5);

        return list;
    }
}

