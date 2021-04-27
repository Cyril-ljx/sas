package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * (SysUserRole)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 263676932032910576L;
    
    private String userId;
    
    private String roleId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

}