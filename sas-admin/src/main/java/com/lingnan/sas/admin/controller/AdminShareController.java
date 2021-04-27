package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingnan.sas.admin.service.*;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author 19399.
 * @date 2021/3/14.
 * @time 19:54
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin")
public class AdminShareController {

    @Resource
    private UploadLogService service;

    @Resource
    private ClockService clockService;


    @Resource
    private DiretionService diretionService;

    @Resource
    private PoliticsTypeService politicsTypeService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/share/List")
    public LayuiVO liveList(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有上传信息-------------");
            return  service.queryAllByLimit((page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有上传信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除班级
     */
    @ResponseBody
    @RequestMapping("/share/del")
    public Object del(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除上传信息-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("uid")));
        } catch (Exception e) {
            log.error("删除上传信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/clock/List")
    public LayuiVO clockList(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有打卡信息-------------");
            return  clockService.queryAllByLimit((page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有打卡信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除班级
     */
    @ResponseBody
    @RequestMapping("/clock/del")
    public Object delClock(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除打卡信息-------------");
            return Response.success(clockService.deleteById(JSONObject.parseObject(jsonBody).getInteger("cid")));
        } catch (Exception e) {
            log.error("删除打卡信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/direction/List")
    public LayuiVO diretionList(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有假期去向信息-------------");
            return  diretionService.queryAllByLimit((page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有假期去向信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除班级
     */
    @ResponseBody
    @RequestMapping("/direction/del")
    public Object delDirection(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除假期去向信息-------------");
            return Response.success(diretionService.deleteById(JSONObject.parseObject(jsonBody).getInteger("did")));
        } catch (Exception e) {
            log.error("删除假期去向信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有政治面貌
     */
    @ResponseBody
    @RequestMapping("politics/allPolitics")
    public Object allPolitics() {
        try {
            log.info("-------------获取所有政治面貌信息-------------");
            return Response.success(politicsTypeService.queryAll());
        } catch (Exception e) {
            log.error("获取所有政治面貌信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
