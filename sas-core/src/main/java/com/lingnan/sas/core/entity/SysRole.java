package com.lingnan.sas.core.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统角色(SysRole)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class SysRole implements Serializable {
    private static final long serialVersionUID = 559472889272322060L;
    
    private Integer id;
    /**
    * 角色名称
    */
    private String name;
    /**
    * 创建时间
    */
    private Date createDate;
    /**
    * 创建人
    */
    private String createBy;
    /**
    * 更新时间
    */
    private Date updateDate;
    /**
    * 更新人
    */
    private String updateBy;
    /**
    * 备注
    */
    private String remarks;
    
    private Object delFlag;
    /**
    * 角色
    */
    private String role;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Object delFlag) {
        this.delFlag = delFlag;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}