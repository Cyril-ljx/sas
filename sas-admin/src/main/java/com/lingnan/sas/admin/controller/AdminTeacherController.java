package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingnan.sas.admin.service.StudentService;
import com.lingnan.sas.admin.service.TeacherService;
import com.lingnan.sas.core.entity.Student;
import com.lingnan.sas.core.entity.Teacher;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Slf4j
@Controller
@RequestMapping("/sas/admin/teacher")
public class AdminTeacherController {

    @Resource
    private TeacherService teacherService;


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/teacherList")
    public LayuiVO liveList(Integer page, Integer limit, String tname, Integer tid, Integer gid) {
        try {
            log.info("-------------查询所有教师信息-------------");
            return teacherService.queryAllByLimit((page - 1) * limit, limit, tname, tid, gid);

        } catch (Exception e) {
            log.info("查询所有教师信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping("del")
    public Object liveDel(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除教师记录-------------");
            return Response.success(teacherService.deleteById(JSONObject.parseObject(jsonBody).getInteger("tid")));
        } catch (Exception e) {
            log.error("删除教师记录失败：" + e.getMessage());
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
            log.info("-------------教师详情-------------");
            return Response.success(teacherService.queryById(JSONObject.parseObject(jsonBody).getInteger("tid")));
        } catch (Exception e) {
            log.error("详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改教师信息
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody Teacher teacher) {
        try {
            log.info("-------------修改教师信息-------------");

            return teacherService.update(teacher);
        } catch (Exception e) {
            log.error("修改教师信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加学生信息
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Teacher teacher) {
        try {
            log.info("-------------添加教师信息-------------");
            return teacherService.add(teacher);
        } catch (Exception e) {
            log.error("添加教师信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
}
