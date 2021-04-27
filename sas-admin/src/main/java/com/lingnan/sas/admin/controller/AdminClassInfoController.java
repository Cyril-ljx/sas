package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.ClassinfoService;
import com.lingnan.sas.core.entity.Classinfo;
import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.entity.Student;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.ClassinfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sas/admin/classInfo")
public class AdminClassInfoController {

    @Resource
    private ClassinfoService classinfoService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO liveList(SchoolManageVO schoolManageVO,Integer page, Integer limit) {

        try {
            log.info("-------------分页查询所有班级信息-------------");
            return classinfoService.queryAllByLimit(schoolManageVO,page,limit);

        } catch (Exception e) {
            log.info("查询所有班级信息失败:{}", e.getMessage());
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
            log.info("-------------删除班级-------------");
            return Response.success(classinfoService.deleteById(JSONObject.parseObject(jsonBody).getInteger("classid")));
        } catch (Exception e) {
            log.error("删除班级失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有班级
     */
    @ResponseBody
    @RequestMapping("/allClass")
    public Object allPolitics() {
        try {
            log.info("-------------获取所有班级信息-------------");
            return Response.success(classinfoService.queryAll());
        } catch (Exception e) {
            log.error("获取所有班级信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 条件查询班级
     */
    @RequestMapping("tjselclass")
    @ResponseBody
    public Object tjSelClass(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = classinfoService.tjSelClass(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> classData = new HashMap<String, Object>();
        classData.put("code", 0);
        classData.put("msg", "");
        classData.put("count", pageInfo.getTotal());
        classData.put("data", pageInfo.getList());
        return classData;
    }

    /**
     * 添加班级
     */
    @RequestMapping("add")
    @ResponseBody
    public Object addOneClass(@RequestBody Classinfo classInfo) {

        try {
            log.info("-------------添加班级信息-------------");
            return classinfoService.addOneClass(classInfo);
        } catch (Exception e) {
            log.error("添加班级信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }

    }
}
