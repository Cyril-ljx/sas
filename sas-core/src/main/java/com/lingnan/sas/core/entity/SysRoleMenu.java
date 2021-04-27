package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * (SysRoleMenu)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class SysRoleMenu implements Serializable {
    private static final long serialVersionUID = -49500969245855314L;
    
    private String roleId;
    
    private String menuId;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}