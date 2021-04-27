package com.lingnan.sas.core.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 登录日志(LoginLog)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class LoginLog implements Serializable {
    private static final long serialVersionUID = -47581320313696562L;
    
    private Integer id;
    /**
    * 登录情况
    */
    private String msg;
    /**
    * 用户ID，只有不是账号不存在的情况下才记录
    */
    private Integer uId;
    /**
    * 是否成功
    */
    private String succeed;
    /**
    * 记录日期
    */
    private String createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUId(Integer uId) {
        this.uId = uId;
    }

    public String getSucceed() {
        return succeed;
    }

    public void setSucceed(String succeed) {
        this.succeed = succeed;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}