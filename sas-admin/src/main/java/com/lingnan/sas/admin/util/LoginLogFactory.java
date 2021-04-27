package com.lingnan.sas.admin.util;


import com.lingnan.sas.core.entity.LoginLog;
import com.lingnan.sas.core.util.DateUtil;

public class LoginLogFactory {
    public static LoginLog success(Integer uid) {
        LoginLog log = new LoginLog();
        log.setMsg("正常登录");
        log.setSucceed("成功");
        log.setUId(uid);
        log.setCreateTime(DateUtil.getNowTime());
        return log;
    }

    public static LoginLog error(String msg) {
        LoginLog log = new LoginLog();
        log.setMsg("登录失败");
        log.setSucceed("失败");
        log.setCreateTime(DateUtil.getNowTime());
        log.setMsg(msg);
        return log;
    }

    public static LoginLog logout(Integer uid, String ip) {
        LoginLog log = new LoginLog();
        log.setMsg("登出");
        log.setSucceed("成功");
        log.setUId(uid);
        log.setCreateTime(DateUtil.getNowTime());
        return log;
    }
}
