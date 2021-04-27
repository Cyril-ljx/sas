package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;

import com.lingnan.sas.admin.service.StudentService;
import com.lingnan.sas.core.entity.*;
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
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/sas/admin/student")
public class AdminStudentController {
 

    @Resource
    private StudentService studentService;


    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/studentList")
    public LayuiVO liveList(Integer page, Integer limit,String sname,Integer sid, Integer classid) {
        try {
            log.info("-------------查询所有学生信息-------------");
            return studentService.queryAllByLimit((page - 1) * limit, limit,sname,sid,classid);

        } catch (Exception e) {
            log.info("查询所有学生信息失败:{}", e.getMessage());
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
            log.info("-------------删除学生记录-------------");
            return Response.success(studentService.deleteById(JSONObject.parseObject(jsonBody).getInteger("sid")));
        } catch (Exception e) {
            log.error("删除学生记录失败：" + e.getMessage());
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
            log.info("-------------学生详情-------------");
            return Response.success(studentService.queryById(JSONObject.parseObject(jsonBody).getInteger("sid")));
        } catch (Exception e) {
            log.error("详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有学生
     */
    @ResponseBody
    @RequestMapping("allStudent")
    public Object allStudent() {
        try {
            log.info("-------------获取所有学生信息-------------");
            return Response.success(studentService.queryAll());
        } catch (Exception e) {
            log.error("获取所有学生信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
    /**
     * 修改学生信息
     */
    @ResponseBody
    @RequestMapping("edit")
    public Object edit(@RequestBody Student student) {
        try {
            log.info("-------------修改学生信息-------------");

            return studentService.update(student);
        } catch (Exception e) {
            log.error("修改学生信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
    /**
     * 添加学生信息
     */
    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Student student) {
        try {
            log.info("-------------添加学生信息-------------");
            return studentService.add(student);
        } catch (Exception e) {
            log.error("添加学生信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }
    //  查询所有专业
    @RequestMapping("selDepartment")
    @ResponseBody
    public LayuiVO<Map> selDepartment(){
        List<Department> selDepartment = studentService.selDepartment();
        LayuiVO result = new LayuiVO();
        result.setData(selDepartment);
        return result;
    }

    //  根据系部查询专业
    @RequestMapping("selectdid")
    @ResponseBody
    public LayuiVO<Map> selMajor(Integer did){
        List<Major> major  =studentService.selMajor(did);
        LayuiVO result = new LayuiVO();
        result.setData(major);
        return result;
    }
    //  根据专业查询年级
    @RequestMapping("selectmid")
    @ResponseBody
    public LayuiVO<Map> selGrade(Integer mid){
        List<Grade> grade = studentService.selGrade(mid);
        LayuiVO result = new LayuiVO();
        result.setData(grade);
        return result;
    }

    //  根据年级查询班级
    @RequestMapping("selectgid")
    @ResponseBody
    public LayuiVO<Map> selClassinfo(Integer gid){
        List<Classinfo> classinfo = studentService.selClassinfo(gid);
        LayuiVO result = new LayuiVO();
        result.setData(classinfo);
        return result;
    }
}
