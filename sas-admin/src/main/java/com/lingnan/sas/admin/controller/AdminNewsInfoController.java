package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingnan.sas.admin.service.NewsinfoService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 公告栏controller
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin/newsInfo")
public class AdminNewsInfoController {
    

    @Resource
    private NewsinfoService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有通知信息-------------");
            return service.queryAllByLimit((page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有通知信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除公告
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除公告-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("nid")));
        } catch (Exception e) {
            log.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 增加公告
     */
    @ResponseBody
    @RequestMapping("insert")
    public Object insert(@RequestBody Newsinfo newsinfo) {
        try {
            log.info("-------------添加公告-------------");
            System.out.println(newsinfo);
            return service.insert(newsinfo);

        } catch (Exception e) {
            log.error("添加公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 详情
     */
    @ResponseBody
    @RequestMapping("detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            log.info("-------------详情-------------");
            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            log.error("详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改公告
     */
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Newsinfo newsinfo) {
        try {
            log.info("-------------修改公告-------------");
            newsinfo.setNtime(DateUtil.getNowTime());
            Newsinfo result = service.update(newsinfo);
            if (result != null) {
                return Response.success(result);
            }
            return Response.error("修改失败");
        } catch (Exception e) {
            log.error("修改公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

}