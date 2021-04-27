package com.lingnan.sas.client.controller;


import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.service.ClockService;
import com.lingnan.sas.client.service.OrganizationService;
import com.lingnan.sas.client.service.StudentService;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.core.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/organization")
//添加@api swagger才会扫描类下面的方法
@Api("组织架构模块接口")
@Slf4j
public class OrganizationController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClockService clockService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private StudentService studentService;


    @PostMapping("/searchDeptByPage")
    @ApiOperation("查询院系信息")
    public R searchDeptByPage(@Valid @RequestBody ClockVO clock) {
        log.info("---------------查询院系信息-------------------");
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<Department> result = organizationService.queryDepByPage(start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchMajorByPage")
    @ApiOperation("查询专业信息")
    public R searchMajorByPage(@Valid @RequestBody ClockVO clock) {
        log.info("---------------查询专业信息-------------------");
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        int id = clock.getId();
        List<Major> result = organizationService.queryMajByPage(id, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchGradeByPage")
    @ApiOperation("查询年级信息")
    public R searchGradeByPage(@Valid @RequestBody ClockVO clock) {
        log.info("---------------查询年级信息-------------------");
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        int id = clock.getId();
        List<Grade> result = organizationService.queryGradeByPage(id, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchClassByPage")
    @ApiOperation("查询班级信息")
    public R searchClassByPage(@Valid @RequestBody ClockVO clock) {
        log.info("---------------查询班级信息-------------------");
        int id = clock.getId();
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<Classinfo> result = organizationService.queryClassByPage(id, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchStuByPage")
    @ApiOperation("查询学生信息")
    public R searchStuByPage(@Valid @RequestBody ClockVO clock) {
        log.info("---------------查询学生信息-------------------");
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        int id = clock.getId();
        List<Student> result = organizationService.queryStuByPage(id, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchStudentById")
    @ApiOperation("学生详细信息")
    public R searchStudentById(@RequestBody ClockVO clock) {
        log.info("---------------学生详细信息------------------");
        Student map = studentService.queryById(clock.getId().toString());
        return R.ok().put("result", map);
    }

}
