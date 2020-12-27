package com.daemon.background.rabbitMQ;

import com.daemon.background.model.UserDTO;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author dell
 * Created on 2019-11-08 09:46
 **/
@Component
public class Customer {

    private Logger logger = LoggerFactory.getLogger(Customer.class);

    /*@RabbitHandler
    @RabbitListener(queues = "topic.queue") //需到管理平台上手动生成队列
    public void receive(UserDTO user,  Message message, Channel channel) {
        //logger.info("【消费者收到的消息】:[{}]", new String(message.getBody()));
        System.out.println("消费者收到消息"+user.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /*@RabbitHandler
    @RabbitListener(queues = "topic.queue") //需到管理平台上手动生成队列
    public void receive(Object msg,  Message message, Channel channel) {
        //logger.info("【消费者收到的消息】:[{}]", new String(message.getBody()));
        System.out.println("消费者收到消息"+msg.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /*@RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("topic.queue"))    //自动生成队列
    public void receive(UserDTO user,  Message message, Channel channel) {
        //logger.info("【消费者收到的消息】:[{}]", new String(message.getBody()));
        System.out.println("消费者收到消息"+user.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @RabbitHandler
    @RabbitListener(queues = "withdrawalQueue1")
    public void receive(Object msg,  Message message, Channel channel) {
        System.out.println("消费者收到消息"+msg.toString());
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
