package com.lingnan.sas.client.controller;


import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.controller.form.TestSayHelloForm;
import com.lingnan.sas.client.service.ClockService;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.core.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/clock")
//添加@api swagger才会扫描类下面的方法
@Api("打卡模块接口")
@Slf4j
public class ClockController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ClockService clockService;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/insertIntoClock")
    @ApiOperation("插入打卡日记")
    public R insertIntoClock(@RequestBody Clock clock, @RequestHeader("token") String token) {
        log.info("---------------插入打卡日记-------------------");
        int id = jwtUtil.getUserId(token);
        int result = clockService.insert(clock,id);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchClockMesById")
    @ApiOperation("查询学生打卡信息")
    public R searchClockMesById(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("---------------查询学生打卡信息-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        long start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = clockService.searchMessageById(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchClock")
    @ApiOperation("根据班级查当天已打卡的学生列表")
    public R searchClock(@Valid @RequestBody ClockVO clock) {
        log.info("---------------根据班级查当天已打卡的学生列表-------------------");
        Integer cid = clock.getId();
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = clockService.searchClock(cid, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchNotClock")
    @ApiOperation("根据班级查当天未打卡的学生列表")
    public R searchNotClock(@Valid @RequestBody ClockVO clock) {
        log.info("---------------根据班级查当天未打卡的学生列表-------------------");
        Integer cid = clock.getId();
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = clockService.searchNotClock(cid, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchClockNum")
    @ApiOperation("按班级查找教师管理的年级已打卡的学生人数")
    public R searchClockNum(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("---------------按班级查找教师管理的年级已打卡的学生人数-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = clockService.searchClockNum(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchNotClockNum")
    @ApiOperation("按班级查找教师管理的年级未打卡的学生人数")
    public R searchNotClockNum(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("---------------按班级查找教师管理的年级未打卡的学生人数-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = clockService.searchNotClockNum(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchClockById")
    @ApiOperation("打卡详细信息")
    public R searchClockById(@RequestBody SearchMessageByIdVO clock) {
        log.info("---------------查询打卡详细信息------------------");
        HashMap map = clockService.searchClockById(Integer.parseInt(clock.getId()));
        return R.ok().put("result", map);
    }

    @PostMapping("/insertDirection")
    @ApiOperation("插入去向信息")
    public R insertDirection(@RequestBody Diretion diretion) {
        log.info("---------------插入去向信息------------------");
        return clockService.insertDiretion(diretion);
    }

    @PostMapping("/searchDirectionNum")
    @ApiOperation("按班级查找教师管理的年级已打卡假期去向的学生人数")
    public R searchDirectionNum(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("---------------按班级查找教师管理的年级已打卡假期去向的学生人数-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = clockService.searchDirectionNum(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchNotDirectionNum")
    @ApiOperation("按班级查找教师管理的年级未打卡假期去向的学生人数")
    public R searchNotDirectionNum(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("---------------按班级查找教师管理的年级已打卡假期去向的学生人数-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = clockService.searchNotDirectionNum(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchNotDirection")
    @ApiOperation("根据班级查当天未打卡假期去向的学生列表")
    public R searchNotDirection(@Valid @RequestBody ClockVO clock) {
        log.info("---------------根据班级查当天未打卡的学生列表-------------------");
        Integer cid = clock.getId();
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = clockService.searchNotDirection(cid, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchDirection")
    @ApiOperation("根据班级查当天已打卡假期去向的学生列表")
    public R searchDirection(@Valid @RequestBody ClockVO clock) {
        log.info("---------------根据班级查当天未打卡的学生列表-------------------");
        Integer cid = clock.getId();
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = clockService.searchDirection(cid, start, length);
        return R.ok().put("result", result);
    }
}
