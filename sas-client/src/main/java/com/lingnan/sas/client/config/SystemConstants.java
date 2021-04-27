package com.lingnan.sas.client.config;

import lombok.Data;
import org.springframework.stereotype.Component;
/*
* 因为不用多例所以变量不声明为static而且用了Component注解为单例相当全局变量
* */
@Data
@Component
public class SystemConstants {
    public String attendanceStartTime;
    public String attendanceTime;
    public String attendanceEndTime;
    public String closingStartTime;
    public String closingTime;
    public String closingEndTime;
}
