package com.cumt.watermark.controller;

import com.cumt.watermark.entity.Order;
import com.cumt.watermark.service.OrderAService;
import com.cumt.watermark.utility.DrawPic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "",tags = "数据A分析")
@EnableSwagger2
@RestController
@RequestMapping("/orderA")
public class OrderAController {

    @Autowired
    private OrderAService oas;

    @ApiOperation(value = "10条orderA ",notes = "查询orderA的前十条记录")
    @RequestMapping(value = "/getTenRecord",method = RequestMethod.GET)
    private Map<String, Object> getTenRecord(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<Order> list = oas.getTenRecord();
        map.put("orderList",list);
        return map;
    }

    @ApiOperation(value = "countListByHour ",notes = "查询各小时的订单数量")
    @RequestMapping(value = "/getStartHoursCount",method = RequestMethod.GET)
    private Map<String, Object> getStartHoursCount(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<Integer> count = oas.getStartHoursCount();
        map.put("countList",count);
        return map;
    }

    @ApiOperation(value = "countListByHour ",notes = "查询各小时的订单数量")
    @RequestMapping(value = "/getEndHoursCount",method = RequestMethod.GET)
    private Map<String, Object> getEndHoursCount(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<Integer> count = oas.getEndHoursCount();
        map.put("countList",count);
        return map;
    }

    @ApiOperation(value = "countListByDuration ",notes = "获取订单耗时数量")
    @RequestMapping(value = "/getDurationCount",method = RequestMethod.GET)
    private Map<String, Object> getDurationCount(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<Integer> count = oas.getDurationCount();
        map.put("countList",count);
        return map;
    }

    @ApiOperation(value = "countListByDurationAndHour ",notes = "获取订单耗时数量")
    @RequestMapping(value = "/getDurationAndHourCount",method = RequestMethod.GET)
    private Map<String, Object> getDurationAndHourCount(){
        Map<String, Object> map = new HashMap<String,Object>();
        List<List<Integer>> count = oas.getDurationCountDivByHour();
        map.put("countList",count);
        return map;
    }

    @ApiOperation(value = "countOfAll ",notes = "查询所有订单数量")
    @RequestMapping(value = "/getAllCount",method = RequestMethod.GET)
    private Map<String, Object> getAllCount(){
        Map<String, Object> map = new HashMap<String,Object>();
        int count = oas.getAllCount();
        map.put("count",count);
        return map;
    }
}
