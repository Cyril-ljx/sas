package com.lingnan.sas.client.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    /*
    同步收发请求需用到ConnectionFactory
    * */
    @Bean
    public ConnectionFactory getFactory(){
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("106.55.170.221");//Linux主机的ip地址
        factory.setPort(5672);//rabbitmq端口号
        factory.setUsername("root");
        factory.setPassword("root");
        return factory;
    }
}
