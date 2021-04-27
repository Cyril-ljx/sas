package com.lingnan.sas.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 年级(Grade)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
public class Grade implements Serializable {
    private static final long serialVersionUID = -90381835251313944L;
    /**
    * 年级id
    */
    private Integer gid;
    /**
    * 年级名称
    */
    private String gname;
    /**
    * 年级总人数
    */
    private Object gcount;
    /**
    * 所属专业
    */
    private Integer mid;
    
    private Integer isDel;

    /**
     * 年级名称
     */
    private String mname;

    List<Classinfo> classinfos;


    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public Object getGcount() {
        return gcount;
    }

    public void setGcount(Object gcount) {
        this.gcount = gcount;
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

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public List<Classinfo> getClassinfos() {
        return classinfos;
    }

    public void setClassinfos(List<Classinfo> classinfos) {
        this.classinfos = classinfos;
    }
}