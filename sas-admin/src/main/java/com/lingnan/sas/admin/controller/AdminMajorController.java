package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.GradeService;
import com.lingnan.sas.admin.service.MajorService;
import com.lingnan.sas.core.entity.Major;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 19399.
 * @date 2021/3/14.
 * @time 19:54
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin/major")
public class AdminMajorController {

    @Resource
    private MajorService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO liveList(SchoolManageVO schoolManageVO,Integer page, Integer limit) {
        try {
            log.info("-------------查询所有专业信息-------------");
            return  service.queryAllByLimit(schoolManageVO,(page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有专业信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除班级
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除专业-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("mid")));
        } catch (Exception e) {
            log.error("删除公告失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有专业
     */
    @ResponseBody
    @RequestMapping("/allGrade")
    public Object allGrade() {
        try {
            log.info("-------------获取所有专业信息-------------");
            return Response.success(service.queryAll());
        } catch (Exception e) {
            log.error("获取所有专业信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 条件查询专业
     */
    @RequestMapping("tjselmajor")
    @ResponseBody
    public Object tjSelMajor(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = service.tjSelMajor(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> majorData = new HashMap<String, Object>();
        majorData.put("code", 0);
        majorData.put("msg", "");
        majorData.put("count", pageInfo.getTotal());
        majorData.put("data", pageInfo.getList());
        return majorData;
    }

    /**
     * 级联获取专业列表
     */
    @RequestMapping("jlselmajor")
    @ResponseBody
    public Object jlSelMajor(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = service.jlSelMajor(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> jlmajorData = new HashMap<String, Object>();
        jlmajorData.put("code", 0);
        jlmajorData.put("msg", "");
        jlmajorData.put("count", pageInfo.getTotal());
        jlmajorData.put("data", pageInfo.getList());
        return jlmajorData;
    }

    /**
     * 添加专业
     */
    @RequestMapping("add")
    @ResponseBody
    public Object addOneMajor(@RequestBody Major major) {

        try {
            log.info("-------------添加专业信息-------------");
            return service.addOneMajor(major);
        } catch (Exception e) {
            log.error("添加专业信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }

    }
}
