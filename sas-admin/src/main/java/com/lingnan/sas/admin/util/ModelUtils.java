package com.lingnan.sas.admin.util;

import com.lingnan.sas.core.entity.SysUser;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

public class ModelUtils {
    public static ModelAndView createModel(HttpServletRequest request, String view) {
        ModelAndView result = new ModelAndView();
        result.setViewName(view);
        if (ShiroUtil.getSubject() != null) {
            SysUser user = (SysUser) ShiroUtil.getSubject().getPrincipal();
            result.addObject("user", user);
        }
        return result;
    }
}
