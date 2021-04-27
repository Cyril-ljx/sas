package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * 团组织类型(PoliticsType)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class PoliticsType implements Serializable {
    private static final long serialVersionUID = -62107678706907463L;
    
    private Integer pid;
    
    private String pname;


    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

}