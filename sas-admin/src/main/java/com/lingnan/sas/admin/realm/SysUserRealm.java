package com.lingnan.sas.admin.realm;


import com.lingnan.sas.admin.service.SysUserService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public class SysUserRealm extends AuthorizingRealm {


    @Autowired
    private SysUserService service;


    /**
     * 权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("——————权限认证——————");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        //获取角色权限
        String roleList = service.getRole(sysUser.getLoginName());
        //添加角色权限
        Set<String> set =new HashSet<>();
        
        List<String> roles = Arrays.asList(roleList.split(","));
        for (String role : roles) {
            System.out.print(role+" ");
            set.add(role);
        }
        simpleAuthorizationInfo.addRoles(set);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("——————身份认证——————");
        UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
        String username = uptoken.getUsername();
        SysUser sysUser = service.findUserByLoginName(username);
        if (sysUser == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (sysUser.getLocked()) {
            throw new AuthenticationException("用户已锁定");
        }
        String password = ShiroUtil.salt(uptoken.getPassword(), sysUser.getSalt());
        if (!sysUser.getPassword().equals(password)) {
            throw new AuthenticationException("密码错误");
        }
        return new SimpleAuthenticationInfo(sysUser, uptoken.getPassword(), getName());
    }
}
