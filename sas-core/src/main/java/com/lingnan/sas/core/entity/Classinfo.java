package com.lingnan.sas.core.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 班级信息(Classinfo)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:13
 */
public class Classinfo implements Serializable {
    private static final long serialVersionUID = 599727156476893125L;
    /**
    * 班级id
    */
    private Integer classid;
    /**
    * 班级名称
    */
    private String classname;
    /**
    * 开始时间
    */
    private Object begintime;
    /**
    * 结束时间
    */
    private Object endtime;
    /**
    * 人数
    */
    private Integer ccount;
    /**
    * 年级
    */
    private Integer gid;
    /**
    * 是否删除
    */
    private Integer isDel;

    /*
    *学生
     */
    private List<Student> students;

    private String gname;


    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Object getBegintime() {
        return begintime;
    }

    public void setBegintime(Object begintime) {
        this.begintime = begintime;
    }

    public Object getEndtime() {
        return endtime;
    }

    public void setEndtime(Object endtime) {
        this.endtime = endtime;
    }

    public Integer getCcount() {
        return ccount;
    }

    public void setCcount(Integer ccount) {
        this.ccount = ccount;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }
}