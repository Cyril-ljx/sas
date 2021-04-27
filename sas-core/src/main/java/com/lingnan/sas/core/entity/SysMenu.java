package com.lingnan.sas.core.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (SysMenu)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class SysMenu implements Serializable {
    private static final long serialVersionUID = -12561626805385963L;
    
    private String id;
    /**
    * 菜单名称
    */
    private String name;
    /**
    * 父菜单
    */
    private String parentId;
    /**
    * 菜单层级
    */
    private Long level;
    /**
    * 父菜单联集
    */
    private String parentIds;
    /**
    * 排序
    */
    private Object sort;
    /**
    * 链接地址
    */
    private String href;
    /**
    * 打开方式
    */
    private String target;
    /**
    * 菜单图标
    */
    private String icon;
    /**
    * 显示背景色
    */
    private String bgColor;
    /**
    * 是否显示
    */
    private Object isShow;
    /**
    * 权限标识
    */
    private String permission;
    
    private String createBy;
    
    private Date createDate;
    
    private String updateBy;
    
    private Date updateDate;
    
    private String remarks;
    
    private Object delFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public Object getIsShow() {
        return isShow;
    }

    public void setIsShow(Object isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

}