package com.lingnan.sas.admin.controller;


import com.lingnan.sas.admin.util.ModelUtils;
import com.lingnan.sas.admin.util.ShiroUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面跳转控制器
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin/page")
public class PageController {





    /**
     * 跳转到首页
     */
    @RequestMapping("index")
    public ModelAndView index(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/index");
    }


    /**
     * 跳转到欢迎页面
     */
    @RequestMapping("welcome")
    public ModelAndView welcome(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/welcome");
    }

    /**
     * 跳转到重置密码界面
     */
    @RequestMapping("reset")
    public ModelAndView reset(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/reset");
    }

    /**
     * 跳转到管理员管理页面
     */
    @RequestMapping("manager/index")
    public ModelAndView managerIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/manager/index");
    }

    /**
     * 修改管理员
     */
    @RequestMapping("manager/edit")
    public ModelAndView managerEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/system/manager/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 管理员添加-
     */
    @RequestMapping("manager/add")
    public ModelAndView managerAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/manager/add");
    }

    /**
     * 跳转到登录日志管理
     */
    @RequestMapping("loginLog/index")
    public ModelAndView logIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/loginlog/index");
    }

    /**
     * 跳转到角色管理页面
     */
    @RequestMapping("role/index")
    public ModelAndView roleIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/role/index");
    }

    /**
     * 角色添加
     */
    @RequestMapping("role/add")
    public ModelAndView roleAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/system/role/add");
    }


    /**
     * 跳转到公告栏管理页面
     */
    @RequestMapping("systemNotice/index")
    public ModelAndView adviseIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/notice/index");
    }

    /**
     * 发布系统公告页面
     */
    @RequestMapping("systemNotice/add")
    public ModelAndView advisementAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/notice/add");
    }


    /**
     * 修改公告
     */
    @RequestMapping("systemNotice/edit")
    public ModelAndView advisementEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/advisement/advisement/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到教师信息管理页面
     */
    @RequestMapping("teacher/index")
    public ModelAndView staffIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/teacher/index");
    }

    /**
     * 添加教师信息
     */
    @RequestMapping("teacher/add")
    public ModelAndView staffAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/teacher/add");
    }


    /**
     * 修改教师信息
     */
    @RequestMapping("teacher/edit")
    public ModelAndView staffEdit(HttpServletRequest request, Integer tid) {
        ModelAndView result = ModelUtils.createModel(request, "/teacher/edit");
        result.addObject("tid", tid);
        return result;
    }
    /**
     * 教师信息
     */
    @RequestMapping("teacher/detail")
    public ModelAndView teacherInfoDetail(HttpServletRequest request, Integer tid) {
        ModelAndView result = ModelUtils.createModel(request, "/teacher/detail");
        result.addObject("tid", tid);
        return result;
    }
    /**
     * 跳转到学生岗位管理页面
     */
    @RequestMapping("student/index")
    public ModelAndView jobIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/student/index");
    }

    /**
     * 添加学生
     */
    @RequestMapping("student/add")
    public ModelAndView jobAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/student/add");
    }


    /**
     * 修改学生信息
     */
    @RequestMapping("student/edit")
    public ModelAndView jobEdit(HttpServletRequest request, Integer sid) {
        ModelAndView result = ModelUtils.createModel(request, "/student/edit");
        result.addObject("sid", sid);
        return result;
    }

    /**
     * 学生信息
     */
    @RequestMapping("student/detail")
    public ModelAndView studentInfoDetail(HttpServletRequest request, Integer sid) {
        ModelAndView result = ModelUtils.createModel(request, "/student/detail");
        result.addObject("sid", sid);
        return result;
    }

    /**
     * 跳转到院系管理页面
     */
    @RequestMapping("department/index")
    public ModelAndView departmentIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/department/index");
    }

    /**
     * 添加院系
     */
    @RequestMapping("department/add")
    public ModelAndView departmentAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/department/add");
    }

    /**
     * 修改院系
     */
    @RequestMapping("department/edit")
    public ModelAndView departmentEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/department/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到专业管理页面
     */
    @RequestMapping("major/index")
    public ModelAndView majorIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/major/index");
    }

    /**
     * 添加专业
     */
    @RequestMapping("major/add")
    public ModelAndView majorAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/major/add");
    }

    /**
     * 修改专业
     */
    @RequestMapping("major/edit")
    public ModelAndView majorEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/major/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到组织管理页面
     */
    @RequestMapping("league/index")
    public ModelAndView leagueIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/league/index");
    }

    /**
     * 添加组织
     */
    @RequestMapping("league/add")
    public ModelAndView leagueAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/league/add");
    }

    /**
     * 修改组织
     */
    @RequestMapping("league/edit")
    public ModelAndView leagueEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/league/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到班级管理页面
     */
    @RequestMapping("grade/index")
    public ModelAndView gradeIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/grade/index");
    }

    /**
     * 添加班级
     */
    @RequestMapping("grade/add")
    public ModelAndView gradeAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/grade/add");
    }

    /**
     * 修改班级
     */
    @RequestMapping("grade/edit")
    public ModelAndView gradeEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/grade/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到年级管理页面
     */
    @RequestMapping("class/index")
    public ModelAndView classIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/class/index");
    }

    /**
     * 添加年级
     */
    @RequestMapping("classInfo/add")
    public ModelAndView classAdd(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/organization/class/add");
    }

    /**
     * 修改年级
     */
    @RequestMapping("class/edit")
    public ModelAndView classEdit(HttpServletRequest request, Integer id) {
        ModelAndView result = ModelUtils.createModel(request, "/organization/class/edit");
        result.addObject("id", id);
        return result;
    }

    /**
     * 跳转到分享区管理页面
     */
    @RequestMapping("share/index")
    public ModelAndView shareIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/share/index");
    }

    /**
     * 跳转到打卡管理页面
     */
    @RequestMapping("clock/index")
    public ModelAndView clockIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/task/clock/index");
    }

    /**
     * 跳转到假期去向管理页面
     */
    @RequestMapping("direction/index")
    public ModelAndView directionIndex(HttpServletRequest request) {
        return ModelUtils.createModel(request, "/task/direction/index");
    }

}
