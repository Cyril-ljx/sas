package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * (Finance)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
public class Finance implements Serializable {
    private static final long serialVersionUID = -65567131543261286L;
    
    private Integer id;
    /**
    * 班级id
    */
    private Integer classid;
    /**
    * 用途
    */
    private String use;
    /**
    * 支出
    */
    private Double pay;
    /**
    * 收入
    */
    private Double income;
    /**
    * 总额
    */
    private Double count;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public Double getPay() {
        return pay;
    }

    public void setPay(Double pay) {
        this.pay = pay;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

}