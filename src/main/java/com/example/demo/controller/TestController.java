package com.example.demo.controller;

import com.example.demo.bean.Test;
import com.example.demo.service.serviceImpl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class TestController {
    @Autowired
    TestServiceImpl testServiceImpl;
    @RequestMapping("getAll")
    public List<Test> selectAll(){
        List<Test> list = testServiceImpl.selectAll();
        return list;
    }
}
