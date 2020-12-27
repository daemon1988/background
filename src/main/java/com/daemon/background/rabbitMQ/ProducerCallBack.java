package com.daemon.background.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author dell
 * Created on 2019-11-08 14:56
 */

@Component
public class ProducerCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    int count = 0;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg) {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("callbackSender UUID: " + correlationData.getId());
        //this.rabbitTemplate.convertAndSend("message.topic", "topic.key", msg, correlationData);
        this.rabbitTemplate.convertAndSend("topic.queue", msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData.getId();
        //System.out.println("消息唯一ID： " + id);
        if (ack) {
            if (++count > 1) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (correlationData != null) {
                System.out.println(sdf.format(new Date()) + ", CorrelationDataId:" + id);
            } else {
                System.out.println(sdf.format(new Date()) + "CorrelationData是空");
            }
            System.out.println( sdf.format(new Date())+",消息成功消费");
        } else {
            //重试机制
            System.out.println(sdf.format(new Date()) +",消息消费失败: " + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("消息没有被成功路由到队列中");
        System.out.println("returnCallback返回的是：" + new String(message.getBody()));
        System.out.println("returnCallback返回的消息的基本属性：" + message.getMessageProperties());
        System.out.println("返回码是："+replyCode);
        System.out.println("返回提示："+replyText);
        System.out.println("交换器：" + exchange);
        System.out.println("路由键："+routingKey);
    }
}
