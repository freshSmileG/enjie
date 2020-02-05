package com.example.demo.producter;

import com.example.demo.bean.Test;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsMessagingTemplate;


@Configuration
public class TopicProducter {
    /**
     *订阅模式的生产者
     **/
        @Autowired
        private JmsMessagingTemplate jmsMessagingTemplate;
        /**
         * @Description 将接受到的消息及消息模式(topic或queue)放到队列里面，然后消费
         * 者只需要正确的添加注解@JmsListener（destination = "目的地"），监听队列消息就会主动获取
         * @Param destination 目的地
         * @Param msg 消息
         * @Date  2019/3/21 14:46
         */
        public void sendMessage(Test test){
            ActiveMQTopic destination = new ActiveMQTopic("topic");
            jmsMessagingTemplate.convertAndSend(destination,test);
        }



}
