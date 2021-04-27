package com.lingnan.sas.admin.util;


import com.lingnan.sas.core.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.UUID;

/**
 * Shiro的工具类
 **/
public class ShiroUtil {
    /**
     * 加盐参数
     */
    public final static String HASH_ALGORITM_NAME = "MD5";

    /**
     * 循环次数
     * HASH_ITERATIONS
     */
    public final static int HASH_ITERATIONS = 1024;

    /**
     * 生成32的随机盐值
     */
    public static String createSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 加盐加密
     *
     * @param srcPwd    原始密码
     * @param saltValue 盐值
     */
    public static String salt(Object srcPwd, String saltValue) {
        return new SimpleHash(HASH_ALGORITM_NAME, srcPwd, saltValue, HASH_ITERATIONS).toString();
    }


    /**
     * 功能描述：获取当前的subject
     *
     * @return
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 输出当前用户信息，通常为登录帐号信息。
     *
     * @return 当前用户信息
     */
    public static String principal() {
        if (getSubject() != null) {
            Object principal = getSubject().getPrincipal();
            return principal.toString();
        }
        return "";
    }


    /**
     * 从shiro获取session
     *
     * @return
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

    /**
     * 当前用户是否有此角色
     *
     * @param roleName 角色名
     * @return
     */
    public static boolean hasRoleName(String roleName) {
        return getSubject() != null && roleName != null
                && roleName.length() > 0 && getSubject().hasRole(roleName);
    }

    /**
     * 验证当前用户是否拥有指定权限,使用时与lacksPermission 搭配使用
     *
     * @param permission 权限名
     * @return 拥有权限：true，否则false
     */
    public static boolean hasPermission(String permission) {
        return getSubject() != null && permission != null
                && permission.length() > 0
                && getSubject().isPermitted(permission);
    }

    /**
     * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
     *
     * @return 通过身份验证：true，否则false
     */
    public static boolean isAuthenticated() {
        return getSubject() != null && getSubject().isAuthenticated();
    }

    /**
     * 认证通过或已记住的用户。与guset搭配使用。
     *
     * @return 用户：true，否则 false
     */
    public static boolean isUser() {
        return getSubject() != null && getSubject().getPrincipal() != null;
    }

    /**
     * 验证当前用户是否为“访客”，即未认证（包含未记住）的用户。用user搭配使用
     *
     * @return 访客：true，否则false
     */
    public static boolean isGuest() {
        return !isUser();
    }

    public static Integer getUserId() {
        SysUser user = (SysUser) getSubject().getPrincipal();
        return user.getId();
    }
}
