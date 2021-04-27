package com.lingnan.sas.core.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
@Data
@ApiModel
public class SysUser implements Serializable {
    private static final long serialVersionUID = 988115651769782608L;
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 登录名
     */
    @NotBlank(message = "登录名不能为空")
    private String loginName;
    /**
     * 昵称
     */
    private String nickName;


    private String icon;
    /**
     * 密码
     */
    private String password;
    /**
     * shiro加密盐
     */
    private String salt;
    /**
     * 手机号码
     */
    private String tel;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 是否锁定
     */
    private Boolean locked;

    private String createDate;

    private String createBy;

    private Date updateDate;

    private String updateBy;

    private String remarks;


    private Boolean isAdmin;

    //微信的openID
    private String openId;
    /**
     * 角色
     */
    private Integer roleId;

    private String Code;

}