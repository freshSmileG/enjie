package com.example.demo.service.serviceImpl;

import com.example.demo.bean.Test;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public List<Test> selectAll() {
        return testMapper.selectAll();
    }
}
