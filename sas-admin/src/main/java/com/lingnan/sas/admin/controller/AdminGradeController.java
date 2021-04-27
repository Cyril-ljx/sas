package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.ClassinfoService;
import com.lingnan.sas.admin.service.GradeService;
import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/sas/admin/grade")
public class AdminGradeController {

    @Resource
    private GradeService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO liveList(SchoolManageVO schoolManageVO,Integer page, Integer limit) {
        try {
            log.info("-------------查询所有年级信息-------------");
            return service.queryAllByLimit(schoolManageVO,(page - 1) * limit, limit);
        } catch (Exception e) {
            log.info("查询所有年级信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除年级
     */
    @ResponseBody
    @RequestMapping("del")
    public Object del(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除年级-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("gid")));
        } catch (Exception e) {
            log.error("删除年级失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有年级
     */
    @ResponseBody
    @RequestMapping("/allGrade")
    public Object allGrade(SchoolManageVO schoolManageVO) {
        try {
            log.info("-------------获取所有年级信息-------------");
            return Response.success(service.queryAll(schoolManageVO));
        } catch (Exception e) {
            log.error("获取所有年级信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 条件查询年级
     */
    @RequestMapping("tjselgrade")
    @ResponseBody
    public Object tjSelGrade(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = service.tjSelGrade(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> gradeData = new HashMap<String, Object>();
        gradeData.put("code", 0);
        gradeData.put("msg", "");
        gradeData.put("count", pageInfo.getTotal());
        gradeData.put("data", pageInfo.getList());
        return gradeData;
    }

    /**
     * 级联获取年级列表
     */
    @RequestMapping("jlselgrade")
    @ResponseBody
    public Object jlSelGrade(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = service.jlSelGrade(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> jlgradeData = new HashMap<String, Object>();
        jlgradeData.put("code", 0);
        jlgradeData.put("msg", "");
        jlgradeData.put("count", pageInfo.getTotal());
        jlgradeData.put("data", pageInfo.getList());
        return jlgradeData;
    }
    /**
     * 添加年级
     */
    @RequestMapping("add")
    @ResponseBody
    public Object addOneGrade(@RequestBody Grade grade) {
        try {
            log.info("-------------获取添加年级-------------");
            return service.addOneGrade(grade);
        } catch (Exception e) {
            log.error("添加年级信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }

    }
}
