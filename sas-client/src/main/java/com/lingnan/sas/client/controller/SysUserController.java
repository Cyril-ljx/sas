package com.lingnan.sas.client.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.service.ClockService;
import com.lingnan.sas.client.service.StudentService;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.client.service.TeacherService;
import com.lingnan.sas.client.util.ShiroUtil;
import com.lingnan.sas.core.entity.*;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.core.vo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Manager;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
@Api("用户模块Web接口")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private ClockService clockService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${sas.jwt.cache-expire}")
    private int cacheExpire;


    @PostMapping("/login")
    @ApiOperation("登陆系统")
    public R login(@Valid @RequestBody SysUser user) {
        log.info("------------------------登录小程序------------------------");
        int id = userService.login(user);
        String token = jwtUtil.createToken(id);
        saveCacheToken(token, id);
        String role = userService.getRole(user.getLoginName());
        return R.ok("登陆成功").put("token", token).put("role", role);
    }

    /**
     * 重置密码
     */
    @PostMapping("updatePassword")
    @ApiOperation("修改密码")
    public Object updatePassword(@RequestBody SysPassword password, @RequestHeader("token") String token) {
            log.info("-------------修改密码-------------");
            SysUser sysUser = (SysUser) ShiroUtil.getSubject().getPrincipal();
            int id = jwtUtil.getUserId(token);
            if(!sysUser.getPassword().equals(ShiroUtil.salt(password.getOldPassword(), sysUser.getSalt()))){
                return R.ok().put("result","旧密码错误");
            }
            sysUser.setId(id);
            sysUser.setPassword(password.getNewPassword());
            Integer result = userService.update(sysUser);
            if(result<=0){
                return R.ok().put("result","修改失败");
            }
            redisTemplate.opsForValue().set(token, id + "", 1L, TimeUnit.SECONDS);
            return R.ok().put("result", "成功修改");
        }

    /**
     * 登出
     */
    @RequestMapping("/loginOut")
    public R logout(@RequestHeader("token") String token) {
        log.info("----------------退出登录------------------");
        SecurityUtils.getSubject().logout();
        int id = jwtUtil.getUserId(token);
        redisTemplate.opsForValue().set(token, id + "", 1L, TimeUnit.SECONDS);
        return R.ok().put("result", "退出");
    }

        @GetMapping("/searchUserSummary")
        @ApiOperation("查询用户摘要信息")
        public R searchUserSummary (@RequestHeader("token") String token){
            log.info("---------------查询用户摘要信息------------------");
            int id = jwtUtil.getUserId(token);
            String username = userService.queryById(id).getLoginName();
            HashMap map = userService.searchUserSummary(username);
            return R.ok().put("result", map);
        }

        @GetMapping("/searchMessage")
        @ApiOperation("查询用户信息")
        public R searchMessage (@RequestHeader("token") String token) throws ParseException {
            log.info("---------------查询用户信息------------------");
            int id = jwtUtil.getUserId(token);
            String username = userService.queryById(id).getLoginName();
            ClockVO clock = clockService.searchMessage(username);
            if (clock.getTime() != null && clock.getTime().equals(DateUtil.getNowDate())) {
                clock.setFlag("1");
            }
            System.out.println(clock.getTime());
            return R.ok().put("result", clock);
        }

        @GetMapping("/searchUserInfo")
        @ApiOperation("查询学生全部信息")
        public R searchUserInfo (@RequestHeader("token") String token) throws ParseException {
            log.info("---------------查询学生全部信息------------------");
            int id = jwtUtil.getUserId(token);
            String username = userService.queryById(id).getLoginName();
            Student student = studentService.queryById(username);
            return R.ok().put("result", student);
        }

        @PostMapping("/updateStuInfo")
        @ApiOperation("更新学生信息信息")
        public R updateStuInfo (@Valid @RequestBody Student student){
            log.info("---------------更新学生信息信息------------------");
            student.setPid(student.getPid() + 1);
            int result = studentService.update(student);
            return R.ok().put("result", result);
        }

        @GetMapping("/searchTeacherInfo")
        @ApiOperation("查询教师全部信息")
        public R searchTeacherInfo (@RequestHeader("token") String token) throws ParseException {
            log.info("---------------查询教师全部信息------------------");
            int id = jwtUtil.getUserId(token);
            String username = userService.queryById(id).getLoginName();
            Teacher teacher = teacherService.queryById(username);
            return R.ok().put("result", teacher);
        }

        @PostMapping("/updateTeacherInfo")
        @ApiOperation("更新教师信息")
        public R updateTeacherInfo (@Valid @RequestBody Teacher teacher){
            log.info("---------------更新教师信息------------------");
            teacher.setPid(teacher.getPid() + 1);
            int result = teacherService.update(teacher);
            return R.ok().put("result", result);
        }

        private void saveCacheToken (String token,int userId){
            redisTemplate.opsForValue().set(token, userId + "", cacheExpire, TimeUnit.DAYS);
        }
    }
