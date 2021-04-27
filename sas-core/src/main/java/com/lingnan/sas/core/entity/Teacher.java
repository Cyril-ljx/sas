package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * 教师表(Teacher)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:58:03
 */
public class Teacher implements Serializable {
    private static final long serialVersionUID = -17851367888664856L;
    /**
    * 教师id
    */
    private Integer tid;

    /**
    * 教师名称
    */
    private String tname;

    /**
    * 性别
    */
    private String tgender;
    /**
    * 生日
    */
    private Object tbirthday;
    /**
    * 政治id
    */
    private Integer pid;
    /**
    * 名族
    */
    private String tnation;
    /**
    * 身份证
    */
    private String tcardid;
    /**
    * 手机
    */
    private String tphone;
    /**
    * 地址
    */
    private String taddress;
    /**
    * 进入时间
    */
    private Object entertime;
    /**
    * 离开时间
    */
    private Object leavetime;
    
    private Integer tchstate;

    private Integer roleId;
    
    private Boolean isDel;

    /**
     * 政治面貌
     */
    private String pname;
    /**
     * 年级
     */
    private String gname;

    private Integer gid;


    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }


    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTgender() {
        return tgender;
    }

    public void setTgender(String tgender) {
        this.tgender = tgender;
    }

    public Object getTbirthday() {
        return tbirthday;
    }

    public void setTbirthday(Object tbirthday) {
        this.tbirthday = tbirthday;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTnation() {
        return tnation;
    }

    public void setTnation(String tnation) {
        this.tnation = tnation;
    }

    public String getTcardid() {
        return tcardid;
    }

    public void setTcardid(String tcardid) {
        this.tcardid = tcardid;
    }

    public String getTphone() {
        return tphone;
    }

    public void setTphone(String tphone) {
        this.tphone = tphone;
    }

    public String getTaddress() {
        return taddress;
    }

    public void setTaddress(String taddress) {
        this.taddress = taddress;
    }

    public Object getEntertime() {
        return entertime;
    }

    public void setEntertime(Object entertime) {
        this.entertime = entertime;
    }

    public Object getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(Object leavetime) {
        this.leavetime = leavetime;
    }

    public Integer getTchstate() {
        return tchstate;
    }

    public void setTchstate(Integer tchstate) {
        this.tchstate = tchstate;
    }

    public Boolean getIsDel() {
        return isDel;
    }

    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
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

}