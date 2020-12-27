package com.daemon.background.rabbitMQ;

import com.daemon.background.model.UserDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dell
 * Created on 2019-11-08 09:46
 **/
/*@Component
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(UserDTO user) {
        System.out.println("【消息发送者】发送消息到fanout交换机，消息内容为: "+ user.toString());
        rabbitTemplate.convertAndSend("message.topic", "topic.key", user);

    }

}*/
