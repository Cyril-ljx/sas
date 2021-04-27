package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * 课程表(Course)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
public class Course implements Serializable {
    private static final long serialVersionUID = -79643936246514547L;
    /**
    * 课程id
    */
    private Integer cid;
    /**
    * 课程名称
    */
    private String cname;
    /**
    * 课程状态
    */
    private Integer cstate;
    
    private Integer cselcount;
    /**
    * 课程类型id
    */
    private Integer ctid;
    /**
    * 所属专业id
    */
    private Integer mid;
    
    private Integer isDel;


    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getCstate() {
        return cstate;
    }

    public void setCstate(Integer cstate) {
        this.cstate = cstate;
    }

    public Integer getCselcount() {
        return cselcount;
    }

    public void setCselcount(Integer cselcount) {
        this.cselcount = cselcount;
    }

    public Integer getCtid() {
        return ctid;
    }

    public void setCtid(Integer ctid) {
        this.ctid = ctid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}