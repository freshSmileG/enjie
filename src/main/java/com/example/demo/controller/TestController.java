package com.example.demo.controller;
import com.example.demo.bean.Test;
import com.example.demo.service.serviceImpl.TestServiceImpl;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import com.example.demo.bean.Test;
import com.example.demo.service.serviceImpl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@ResponseBody
public class TestController {
    @Autowired
    Redisson redisson;
    @Autowired
    TestServiceImpl testServiceImpl;
    //加入redis做缓存
    @Autowired
    private StringRedisTemplate stringredisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("getAll")
    public List<Test> selectAll(){
        List<Test> list = testServiceImpl.selectAll();
        return list;
    }

    @RequestMapping("getById")
    public Test selectById( Test test){
        Test t = (Test) redisTemplate.opsForValue().get("test");
        if (t!=null){
            return t;
            //return null;
        }
        else{
            t = testServiceImpl.selectById(test);
            System.out.println("加入"+"redis");
            redisTemplate.opsForValue().set("test",t);
            return t;
        }
    }

    //实现分布式锁
    public boolean get(){
        String lock = "lock";
        RLock lock1 = redisson.getLock(lock);
        //
        try {
            lock1.lock();//加上redis锁
            int count = Integer.parseInt(stringredisTemplate.opsForValue().get("count"));
            if (count>0) {
                count = count - 1;
                redisTemplate.opsForValue().set("count", count);
                stringredisTemplate.delete(lock);
                return true;
            }
            else return false;
        }
        finally {
            //释放redis锁
            lock1.unlock();
        }


    }




}
