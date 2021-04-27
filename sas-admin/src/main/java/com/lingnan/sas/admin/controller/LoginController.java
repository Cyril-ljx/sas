package com.lingnan.sas.admin.controller;

import com.lingnan.sas.admin.util.ModelUtils;
import com.lingnan.sas.admin.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 19399.
 * @date 2021/3/27.
 * @time 2:31
 */
@Slf4j
@Controller
@RequestMapping("/sas/page")
public class LoginController {
    /**
     * 跳转到登录页面
     */
    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request) {
        if (ShiroUtil.isUser()) {
            return ModelUtils.createModel(request, "/index");
        }
        return new ModelAndView("/login");
    }
    /**
     * 跳转到错误页面
     */
    @RequestMapping("error")
    public ModelAndView error(HttpServletRequest request) {
        log.info("------------访问了error接口");
        return ModelUtils.createModel(request, "/error");
    }

}
