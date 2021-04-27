package com.lingnan.sas.client.aop;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.ThreadLocalToken;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 19399.
 * @date 2021/3/25.
 * @time 15:29
 */
@Aspect
@Component
public class TokenAspect {

    @Autowired
    private ThreadLocalToken threadLocalToken;

    //拦截web方法返回的结果
    @Pointcut("execution(public * com.example.emos.wx.controller.*.*(..))")
    public void aspect(){

    }

    @Around("aspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        R r=(R)point.proceed();//方法执行结果
        String token=threadLocalToken.getToken();
        //如果ThreadLocal中存在Token,说明是更新的Token
        if(token!=null){
            r.put("token",token);//往响应中放置token
            threadLocalToken.clear();
        }
        return r;
    }
}
