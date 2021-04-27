package com.lingnan.sas.core.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生(Student)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class Student implements Serializable {
    private static final long serialVersionUID = -56321569880207716L;

    private Integer sid;

    //年级id
    private Integer gid;
    //院系id
    private Integer did;
    //专业id
    private Integer mid;

    private String stuid;
    /**
     * 学生名称
     */
    private String sname;
    /**
     * 性别
     */
    private String sgender;
    /**
     * 生日
     */
    private Object sbirthday;
    /**
     * 政治面貌
     */
    private Integer pid;
    /**
     * 民族
     */
    private String snation;
    /**
     * 身份证
     */
    private String scardid;
    /**
     * 手机
     */
    private String sphone;
    /**
     * 地址
     */
    private String saddress;
    /**
     * 入学时间
     */
    private String entertime;
    /**
     * 离开时间
     */
    private Object leavetime;
    /**
     * 状态
     */
    private Integer stustate;

    private Integer classid;

    private Boolean isDel;

    private String mim;

    private String pname;

    private String gname;

    private String classname;

    private Integer roleId;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSgender() {
        return sgender;
    }

    public void setSgender(String sgender) {
        this.sgender = sgender;
    }

    public Object getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Object sbirthday) {
        this.sbirthday = sbirthday;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getSnation() {
        return snation;
    }

    public void setSnation(String snation) {
        this.snation = snation;
    }

    public String getScardid() {
        return scardid;
    }

    public void setScardid(String scardid) {
        this.scardid = scardid;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getEntertime() {
        return entertime;
    }

    public void setEntertime(String entertime) {
        this.entertime = entertime;
    }

    public Object getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Object leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getStustate() {
        return stustate;
    }

    public void setStustate(Integer stustate) {
        this.stustate = stustate;
    }

    public Integer getClassid() {
        return classid;
    }

    public void setClassid(Integer classid) {
        this.classid = classid;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getMim() {
        return mim;
    }

    public void setMim(String mim) {
        this.mim = mim;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
}