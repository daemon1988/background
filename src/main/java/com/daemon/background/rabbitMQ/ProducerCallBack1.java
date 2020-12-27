package com.daemon.background.rabbitMQ;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author dell
 * Created on 2019-11-08 14:56
 */

@Component
//public class ProducerCallBack1 implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
public class ProducerCallBack1 {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*@PostConstruct
    public void init(){
        this.rabbitTemplate.setReturnCallback(this);
        this.rabbitTemplate.setConfirmCallback(this);
    }*/


    /*@Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        if (ack){
            if (correlationData != null) {
                System.out.println(sdf.format(new Date())+"时刻，correlationDataId:"+correlationData.getId());
            }else{
                System.out.println(sdf.format(new Date())+"时刻，correlationData为空！");
            }
            System.out.println("消息成功到达交换器exchange");
        }else{
            System.out.println(sdf.format(new Date())+"时刻，消息没有到达交换器exchange");
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

        System.out.println(sdf.format(new Date())+"时刻，返回码是："+replyCode);
        System.out.println(sdf.format(new Date())+"时刻，返回提示："+replyText);
        System.out.println(sdf.format(new Date())+"时刻，交换器是："+exchange);
        System.out.println(sdf.format(new Date())+"时刻，路由键是："+routingKey);
        System.out.println(sdf.format(new Date())+"时刻，message:"+new String(message.getBody()));
    }*/

    public void send(Object object){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        System.out.println(sdf.format(new Date())+"时刻，correlatonData的id："+correlationData.getId());
        System.out.println(sdf.format(new Date())+"时刻，生产者发送了一条消息"+ object);
        rabbitTemplate.convertAndSend("withdrawalQueue1",object,correlationData);
    }
}
