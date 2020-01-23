package com.example.demo.service;

import com.example.demo.bean.Test;

import java.util.List;

public interface TestService {
    public List<Test> selectAll();
    public Test selectById(Test test);

}
