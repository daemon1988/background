package com.daemon.background.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dell
 * Created on 2019-11-08 11:10
 **/
/*@Configuration
public class RabbitMQConfig {

    *//**
     * 创建广播模式Fanout交换机
     *//*
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("message.fanout");
    }

    *//**
     * 创建topic模式交换机
     *//*
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("message.topic");
    }

    *//**
     * 创建fanout队列
     *//*
    @Bean
    public Queue queue() {
        return new Queue("fanout.queue");
    }

    *//**
     * 创建topic队列
     *//*
    @Bean
    public Queue topicQueue() {
        return new Queue("topic.queue");
    }

    *//**
     * 绑定fanout队列到交换机
     *//*
    @Bean
    public Binding bindingFanout() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }

    *//**
     * 绑定topic队列到交换机
     *//*
    @Bean
    public Binding bindingTopic() {
        return BindingBuilder.bind(topicQueue()).to(topicExchange()).with("topic.key");
    }
}*/
