package com.example.demo.mapper;

import com.example.demo.bean.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestMapper {
    public List<Test> selectAll();
    public Test selectById(Test test);

}
