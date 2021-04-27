package com.lingnan.sas.client.task;

import com.lingnan.sas.client.Exception.ClientException;
import com.lingnan.sas.client.service.MessageService;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;
import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQBasicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 19399.
 * @date 2021/3/30.
 * @time 1:11
 */
@Component
@Slf4j
public class MessageTask {

    @Autowired
    private ConnectionFactory factory;

    @Autowired
    private MessageService messageService;

    //topic为rabbitmq的消息名字,发送消息是以ref方式发送，消息主体存在MessageEntity集合里
    public void send(String topic, MessageEntity entity) {
        String id = messageService.insertMessage(entity);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            //连接队列，true持久化存储，false不使用排他锁，false是否自动删除队列
            channel.queueDeclare(topic, true, false, false, null);
            //ref要存储的东西
            HashMap map = new HashMap();
            map.put("messageId", id);
            //将map写到AMQP协议的请求头
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().headers(map).build();
            //发送消息
            channel.basicPublish("", topic, properties, entity.getMsg().getBytes());
            log.debug("消息发送成功");
        } catch (Exception e) {
            log.error("执行异常", e);
            throw new ClientException("向MQ发送消息失败");
        }
    }

    @Async
    public void sendAsync(String topic, MessageEntity entity) {
        send(topic, entity);
    }

    //从哪个消息队列接收数据
    public int receive(String topic) {
        int i = 0;
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            //连接队列，true持久化存储，false不使用排他锁，false是否自动删除队列
            channel.queueDeclare(topic, true, false, false, null);
            while (true) {
                //false意思为接收消息不自动返回ack应答
                GetResponse response = channel.basicGet(topic, false);
                if (response != null) {
                    //从响应提取绑定数据
                    AMQP.BasicProperties properties = response.getProps();
                    Map<String, Object> map = properties.getHeaders();
                    String messageId = map.get("messageId").toString();
                    byte[] body = response.getBody();
                    String message = new String(body);
                    log.debug("从RabbitMQ接收的消息：" + message);

                    //往ref保存数据
                    MessageRefEntity entity = new MessageRefEntity();
                    entity.setMessageId(messageId);
                    entity.setReceiverId(Integer.parseInt(topic));
                    //表示消息未读
                    entity.setReadFlag(false);
                    //表示为最新消息
                    entity.setLastFlag(true);
                    messageService.insertRef(entity);
                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    //返回ACK应答
                    channel.basicAck(deliveryTag, false);
                    i++;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            log.error("执行异常", e);
            throw new ClientException("向MQ发送消息失败");
        }
        return i;
    }
    //从哪个消息队列接收数据
    public int batchReceive(String topic,List<MessageRefEntity> entitys) {
        int i = 0;
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            //连接队列，true持久化存储，false不使用排他锁，false是否自动删除队列
            channel.queueDeclare(topic, true, false, false, null);
            while (true) {
                //false意思为接收消息不自动返回ack应答
                GetResponse response = channel.basicGet(topic, false);
                if (response != null) {
                    //从响应提取绑定数据
                    AMQP.BasicProperties properties = response.getProps();
                    Map<String, Object> map = properties.getHeaders();
                    String messageId = map.get("messageId").toString();
                    byte[] body = response.getBody();
                    String message = new String(body);
                    log.debug("从RabbitMQ接收的消息：" + message);

                    //往ref保存数据
                for(MessageRefEntity entity : entitys) {
                    entity.setMessageId(messageId);
                    //表示消息未读
                    entity.setReadFlag(false);
                    //表示为最新消息
                    entity.setLastFlag(true);
                }
                    messageService.batchInsertRef(entitys);
                    long deliveryTag = response.getEnvelope().getDeliveryTag();
                    //返回ACK应答
                    channel.basicAck(deliveryTag, false);
                    i++;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            log.error("执行异常", e);
            throw new ClientException("向MQ发送消息失败");
        }
        return i;
    }

    @Async
    public int receiveAsync(String topic) {
        return receive(topic);
    }

    @Async
    public int batchReceiveAsync(String topic,List<MessageRefEntity> entitys) {
        return batchReceive(topic,entitys);
    }

    public void deleteQueue(String topic){
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel();
        ) {
            channel.queueDelete(topic);
            log.debug("消息队列成功删除");
        }catch (Exception e) {
            log.error("删除队列失败", e);
            throw new ClientException("删除队列失败");
        }
    }

    @Async
    public void deleteQueueAsync(String topic){
        deleteQueue(topic);
    }
}
