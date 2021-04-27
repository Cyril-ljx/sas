package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;

import com.lingnan.sas.admin.service.LoginLogService;
import com.lingnan.sas.admin.service.SysUserService;
import com.lingnan.sas.admin.util.LoginLogFactory;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;


/**
 * 登录控制器
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin/")
public class AdminLoginController {
    

    @Resource
    private LoginLogService service;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping("login")
    public Object login(HttpServletRequest request, @RequestBody String jsonBody) {
        try {
            log.info("-------------登录-------------");
            JSONObject params = (JSONObject) JSONObject.parse(jsonBody);
            String account = params.getString("username");
            String pwd = params.getString("pwd");

            //TODO
            /*
            *非管理员则不允许登录直接返回错误信息
            * */
            String roleList = sysUserService.getRole(account);
            List<String> roles = Arrays.asList(roleList.split(","));
            for (String role : roles) {
                if(role.equals("admin")){
                    Subject subject = SecurityUtils.getSubject();
                    UsernamePasswordToken token = new UsernamePasswordToken(account, pwd);

                    //shiro验证登录
                    subject.login(token);
                    service.insert(LoginLogFactory.success(ShiroUtil.getUserId()));
                    return Response.success("ok");
                }
            }
            return  Response.error("无权限登录");

        } catch (Exception e) {
            service.insert(LoginLogFactory.error(e.getMessage()));
            log.error("登录失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 登出
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        service.insert(LoginLogFactory.logout(ShiroUtil.getUserId(), request.getRemoteAddr()));
        ShiroUtil.getSubject().logout();
        return "/login";
    }
}
