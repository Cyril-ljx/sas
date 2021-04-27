package com.lingnan.sas.core.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
@Data
@ApiModel
public class SysPassword implements Serializable {
    private static final long serialVersionUID = 988115651769782608L;

    /**
     * 旧密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String newPassword;
}