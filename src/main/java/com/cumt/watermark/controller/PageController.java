package com.cumt.watermark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class PageController {
    @RequestMapping("/originData")
    public String userLoginHtml(HashMap<String, Object> map) {
        return "originData.html";
    }
    @RequestMapping("/")
    public String index(HashMap<String, Object> map) {
        return "originData.html";
    }
    @RequestMapping("/markData")
    public String markDataHtml(HashMap<String, Object> map) {
        return "markData.html";
    }
    @RequestMapping("/markProcess")
    public String  markProcessHtml(HashMap<String, Object> map) {
        return "markProcess.html";
    }
    @RequestMapping("/removeMark")
    public String removeMarkHtml(HashMap<String, Object> map) {
        return "removeMark.html";
    }
}
