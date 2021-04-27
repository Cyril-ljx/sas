package com.lingnan.sas.admin.controller;

import com.lingnan.sas.admin.service.LoginLogService;
import com.lingnan.sas.core.entity.LoginLog;
import com.lingnan.sas.core.util.CommonResponse;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志控制器
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin/log")
public class AdminLogController {

    @Resource
    private LoginLogService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/loginList")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有登录日志信息-------------");
            List<LoginLog> clients = service.queryAdminList((page - 1) * limit, limit);
            LayuiVO layData = new LayuiVO();
            layData.setCode(0);
            layData.setMsg("");
            layData.setCount(service.queryAdminCount());//总数
            layData.setData(clients);
            return layData;
        } catch (Exception e) {
            log.info("查询所有登录日志信息失败:{}", e.getMessage());
            return null;
        }
    }


    /**
     * 全部删除登录日记
     */
    @ResponseBody
    @RequestMapping("delAll")
    public Object delAll() {
        try {
            log.info("-------------全部删除日记-------------");
            return service.deleteAll();

        } catch (Exception e) {
            log.error("删除日记失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 批量删除登录日记
     */
    @ResponseBody
    @RequestMapping("batchDelete")
    public Object batchDelete(@RequestBody String ids) {
        try {
            log.info("-------------批量删除日记-------------");
            if (StringUtils.isEmpty(ids)) {
                return Response.error("无id传入");
            }
            /*if (ids == null || ids.isEmpty()) {
                return Response.error("无id传入");
            }*/
            return service.batchDelete(ids);
        } catch (Exception e) {
            log.error("删除日志失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


}
