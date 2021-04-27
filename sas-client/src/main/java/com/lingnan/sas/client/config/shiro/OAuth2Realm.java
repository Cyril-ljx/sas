package com.lingnan.sas.client.config.shiro;

import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.client.util.ShiroUtil;
import com.lingnan.sas.core.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class OAuth2Realm extends AuthorizingRealm {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SysUserService service;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection collection) {
        log.info("--------------权限认证---------------");
        SysUser sysUser = (SysUser) collection.getPrimaryPrincipal();
        //获取角色权限
        String roleList = service.getRole(sysUser.getLoginName());
        //添加角色权限
        Set<String> set = new HashSet<>();

        List<String> roles = Arrays.asList(roleList.split(","));
        for (String role : roles) {
            System.out.print(role + " ");
            set.add(role);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //TODO 查询用户的权限列表
        info.setRoles(set);
        return info;
    }

    /**
     * 认证(验证登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("--------------------身份验证------------------------");
        //得到字符串型令牌
        String accessToken = (String) token.getPrincipal();
        int userId = jwtUtil.getUserId(accessToken);
        SysUser sysUser = service.queryById(userId);
        if (sysUser == null) {
            throw new LockedAccountException("用户不存在或账号已锁定，请联系管理员");
        }
        //getName为realm类的名字
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(sysUser, accessToken, getName());
        return info;
    }
}
