package com.lingnan.sas.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 专业(Major)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class Major implements Serializable {
    private static final long serialVersionUID = -62843080531067973L;
    /**
    * 专业id
    */
    private Integer mid;
    /**
    * 专业名称
    */
    private String mname;
    /**
    * 总数
    */
    private Integer mcount;
    /**
    * 所属系
    */
    private Integer did;
    
    private Integer isDel;

    private List<Grade> grades;

    /**
     * 院系名称
     */
    private String dname;


    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getMcount() {
        return mcount;
    }

    public void setMcount(Integer mcount) {
        this.mcount = mcount;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }
}