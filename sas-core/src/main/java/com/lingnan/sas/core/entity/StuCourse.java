package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * (StuCourse)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class StuCourse implements Serializable {
    private static final long serialVersionUID = 768155949079084233L;
    /**
    * 学生课程id
    */
    private Integer scid;
    /**
    * 学生id
    */
    private Integer sid;
    /**
    * 课程id
    */
    private Integer cid;
    
    private Integer isDel;


    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}