package com.cumt.watermark.controller;

import com.cumt.watermark.service.UtilService;
import com.cumt.watermark.service.impl.UtilServiceImpl;
import com.cumt.watermark.utility.DrawPic;
import com.cumt.watermark.utility.Interface.Wmark_process;
import com.cumt.watermark.utility.impl.Wmark_processImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.net.URLDecoder.*;

@Api(value = "",tags = "水印处理")
@EnableSwagger2
@RestController
@RequestMapping(value = "/mark",
        produces = {"application/json; charset=UTF-8"})
public class MarkController {
    @ApiOperation(value = "firmIcon",notes = "firmIcon")
    @RequestMapping(value = "/createImage",method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    private Map<String, Object>createImage(String firmSign, HttpServletRequest request) throws FileNotFoundException, UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<String,Object>();
       // String path ="static/img/javaPic.bmp";
        //String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/javaPic.bmp";
//        String path = request.getServletContext().getRealPath("d:\\");

        String path = "d:\\";
        //String path = "D:\\";
        File file = DrawPic.createImage(path+"javaPic.bmp",firmSign);

        map.put("firmIcon",file);
        return map;
    }

    @ApiOperation(value = "imageBinary",notes = "获取二值序列")
    @RequestMapping(value = "/imageBinary",method = RequestMethod.POST)
    private Map<String, Object>imageBinary(String path){
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        int[] binNum = us.getBSnum(path);
        map.put("imageBinary", binNum);
        for(int i =0;i<binNum.length;i++){
            System.out.print(binNum[i]);
            if((i+1)%64==0){
                System.out.println();
            }
        }
        return map;
    }
    @ApiOperation(value = "imagePath",notes = "二值序列恢复成图片")
    @RequestMapping(value = "/image",method = RequestMethod.POST)
    private Map<String, Object>image(int[]W_B, HttpServletRequest request) throws FileNotFoundException {
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        //String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/restorePic.bmp";
        //String path ="static/img/restorePic.bmp";
        //String path = ResourceUtils.getURL("classpath:").getPath()+"static/img/restorePic.bmp";
        //final File file = new File("C:\\Users\\tsevc\\Documents\\workspace\\watermark\\target\\classes\\static\\img\\javaPic.bmp");
        //String path = "/bin/restorePic.bmp";
        //String path = request.getServletContext().getRealPath("d:\\");
        String path = "d:\\";
        //String path = "D:\\";
        File f = us.image(W_B,path+"restorePic.bmp");
        System.out.println(path);
        map.put("image", f);
        return map;
    }
    @ApiOperation(value = "getMarkSeq",notes = "获取水印序列")
    @RequestMapping(value = "/getMarkSeq",method = RequestMethod.POST)
    private Map<String, Object>getMarkSeq(int[] I){
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        int [] W = us.getMarkSeq(I);
        map.put("markSequence", W);
        return map;
    }
    @ApiOperation(value = "restoreMark",notes = "水印序列还原成图片序列")
    @RequestMapping(value = "/restoreMark",method = RequestMethod.POST)
    private Map<String, Object>restoreMark(int[] W){
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        int [] I = us.restoreMarkSeq(W);
        map.put("imageBinary",I);
        return map;
    }
    @ApiOperation(value = "addMark",notes = "加水印")
    @RequestMapping(value = "/addMark",method = RequestMethod.POST)
    private Map<String, Object>addMark(int[] W){
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        if(us.addMark(W)){
            map.put("code",0);
            map.put("message","添加成功");
        }
        else
        {
            map.put("code",-1);
            map.put("message","添加失败");
        }
        return map;
    }
    @ApiOperation(value = "pickMark",notes = "提取水印")
    @RequestMapping(value = "/pickMark",method = RequestMethod.POST)
    private Map<String, Object>pickMark(){
        Map<String, Object> map = new HashMap<String,Object>();
        UtilService us = new UtilServiceImpl();
        int[] W = us.pickMark();
        if(W.length == 0){
            map.put("code",-1);
            map.put("message","数据表B为空");
        }
        else
        {
            map.put("code",0);
            map.put("message","提取成功");
            map.put("markSeq",W);
        }
        return map;
    }
    @ApiOperation(value = "pickMark",notes = "根据百分比提取水印")
    @RequestMapping(value = "/pickMarkByPart",method = RequestMethod.POST)
    private Map<String, Object>pickMarkByPart(double persent){
        Map<String, Object> map = new HashMap<String,Object>();
       // UtilService us = new UtilServiceImpl();
        Wmark_process wp = new Wmark_processImpl();
        int[] W = wp.pikup_partofwatermark(persent);
        if(W.length == 0){
            map.put("code",-1);
            map.put("message","数据表B为空");
        }
        else
        {
            map.put("code",0);
            map.put("message","提取成功");
            map.put("markSeq",W);
        }
        return map;
    }
}
