package com.example.demo.TopicCustomer;

import com.example.demo.bean.Test;
import com.example.demo.service.serviceImpl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//发布订阅模式消费者
@Component
public class TopicCustomer {

    @Autowired
    TestServiceImpl testServiceImpl;
        //创建消费者
        @JmsListener(destination = "topic")
        public void subscriber(Test test) {
            testServiceImpl.addTest(test);
            System.out.println("消费者消费+"+test.getId());
        }

}
