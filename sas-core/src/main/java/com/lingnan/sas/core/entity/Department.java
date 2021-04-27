package com.lingnan.sas.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 系(Department)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
public class Department implements Serializable {
    private static final long serialVersionUID = 237813104251191380L;
    /**
    * 系id
    */
    private Integer did;
    /**
    * 系名称
    */
    private String dname;
    /**
    * 系人数
    */
    private Integer dcount;
    
    private Integer isDel;

    List<Major> majors;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getDcount() {
        return dcount;
    }

    public void setDcount(Integer dcount) {
        this.dcount = dcount;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }


    public List<Major> getMajors() {
        return majors;
    }

    public void setMajors(List<Major> majors) {
        this.majors = majors;
    }
}