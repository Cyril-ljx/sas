package com.lingnan.sas.client.task;

import com.lingnan.sas.client.service.DeliverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling
public class WorkTaskScheduling {

    @Autowired
    private DeliverService deliverService;

    @Scheduled(cron = "0 0 2 * * ?")
    public void checkOutDate() {
        log.info("----定时任务开始检测已超过当前时间三天的假期去向----------");
        try {
            deliverService.deleteAll();
        } catch (Exception e) {
            log.info("定时任务检测已超过当前时间三天的假期去向失败:{}", e.getMessage());
        }
    }
}