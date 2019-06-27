package com.cumt.watermark.controller;

import com.cumt.watermark.entity.MarkRecord;
import com.cumt.watermark.service.MarkRecordService;
import com.cumt.watermark.service.UtilService;
import com.cumt.watermark.service.impl.MarkRecordServiceImpl;
import com.cumt.watermark.service.impl.UtilServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "",tags = "水印记录")
@EnableSwagger2
@RestController
@RequestMapping(value = "/markRecord",
        produces = {"application/json; charset=UTF-8"})
public class MarkRecordController {

    @Autowired
    MarkRecordService markRecordService ;

    @ApiOperation(value = "markRecord",notes = "通过firmSign查询markRecord")
    @RequestMapping(value = "/searchByFirmSign",method = RequestMethod.POST)
    private Map<String, Object> searchByFirmSign(String firmSign) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<String,Object>();
        firmSign = "\""+firmSign+"\"";
        List<MarkRecord> markRecord = markRecordService.searchByFirmSign(firmSign);
        if(markRecord.get(0).getFirmName()!="" && markRecord.get(0).getFirmName()!=null && !markRecord.isEmpty()){
            map.put("code",0);
            map.put("message","查询成功");
            map.put("markRecord", markRecord);
        }
        else{
            map.put("code",-1);
            map.put("message","未查到相关记录");
        }
        return map;
    }

    @ApiOperation(value = "markRecord",notes = "通过firmSign查询markRecord")
    @RequestMapping(value = "/insertMarkRecord",method = RequestMethod.POST)
    private Map<String, Object> insertMarkRecord(String firmName, String firmSign){
        Map<String, Object> map = new HashMap<String,Object>();
        boolean flag = markRecordService.insertMarkRecord("order_20161110",firmName,firmSign);
        if(flag){
            map.put("code",0);
            map.put("message","插入成功");
        }
        else{
            map.put("code",-1);
            map.put("message","插入失败");
        }
        return map;
    }
}
