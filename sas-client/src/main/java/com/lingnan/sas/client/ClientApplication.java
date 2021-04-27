package com.lingnan.sas.client;

import cn.hutool.core.util.StrUtil;
import com.lingnan.sas.client.config.SystemConstants;
import com.lingnan.sas.core.entity.SysConfig;
import com.lingnan.sas.mapper.SysConfigDao;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
@EnableAsync
@Slf4j
@MapperScan("com.lingnan.sas.mapper")
@SpringBootApplication(scanBasePackages = {"com.lingnan.sas.client"})
@EnableTransactionManagement
@ServletComponentScan("com.lingnan.sas.client")
public class ClientApplication {

    @Autowired
    private SysConfigDao sysConfigDao;

    @Autowired
    private SystemConstants constants;

    @Value("${sas.image-folder}")
    private String imageFolder;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

/*    @PostConstruct
    public void init() {
        List<SysConfig> list = sysConfigDao.selectAllParam();
        //one封装类对象，一个为常量名字一个为常量的值
        for (SysConfig one : list) {
            String key = one.getParamKey();

            key = StrUtil.toCamelCase(key);//转正驼峰
            String value = one.getParamValue();
            try {
                //通过反射获取常量的类然后再通过key获取字段
                Field field = constants.getClass().getDeclaredField(key);
                field.set(constants, value);
            } catch (Exception e) {
                //根据key找不到field时则会出现异常
                log.error("执行异常", e);
            }
        }
        new File(imageFolder).mkdirs();
//        生成测试数据

    }*/

}