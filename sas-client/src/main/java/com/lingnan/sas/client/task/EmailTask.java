package com.lingnan.sas.client.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author 19399.
 * @date 2021/3/29.
 * @time 1:15
 */
//多例，每个线程都是独立的，无线程安全问题
@Component
@Scope("prototype")
public class EmailTask implements Serializable {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${sas.email.system}")
    private String mailbox;

    @Async
    public void sendAsync(SimpleMailMessage message) {
        message.setFrom(mailbox);
        //抄送给自己
        message.setCc(mailbox);
        javaMailSender.send(message);
    }
}
