package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.lingnan.sas.admin.service.SysRoleService;
import com.lingnan.sas.admin.service.SysUserService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.SysRole;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 管理员控制器
 */
@Slf4j
@Controller
@RequestMapping("/sas/admin")
public class AdminSysUserController {


    @Resource
    private SysUserService service;

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("manager/list")
    public LayuiVO selectAll(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有管理员信息-------------");

            return service.queryAdminList((page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有管理员信息失败:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 管理员详情
     */
    @ResponseBody
    @RequestMapping("manager/detail")
    public Object detail(@RequestBody String jsonBody) {
        try {
            log.info("-------------管理员详情-------------");

            return Response.success(service.queryById(JSONObject.parseObject(jsonBody).getInteger("id")));

        } catch (Exception e) {
            log.error("管理员详情失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加管理员
     */
    @ResponseBody
    @RequestMapping("manager/add")
    public Object add(@RequestBody SysUser sysUser) {
        try {
            log.info("-------------添加管理员-------------");

            return service.insert(sysUser);
        } catch (Exception e) {
            log.error("添加管理员失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 修改系统用户信息
     */
    @ResponseBody
    @RequestMapping("manager/edit")
    public Object edit(@RequestBody SysUser sysUser) {
        try {
            log.info("-------------修改管理员信息-------------");

            return service.update(sysUser);
        } catch (Exception e) {
            log.error("修改管理员信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 重置密码
     */
    @ResponseBody
    @RequestMapping("manager/reset")
    public Object reset(@RequestBody String jsonBody) {
        try {
            log.info("-------------重置密码-------------");
            SysUser sysUser = (SysUser) ShiroUtil.getSubject().getPrincipal();
            String pwd = JSONObject.parseObject(jsonBody).getString("pwd");
            sysUser.setPassword(pwd);
            return service.update(sysUser);
        } catch (Exception e) {
            log.error("重置密码失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }


    /**
     * 删除系统用户
     */
    @ResponseBody
    @RequestMapping("manager/del")
    public Object del(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除管理员-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("id")));
        } catch (Exception e) {
            log.error("删除管理员失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 添加角色
     */
    @ResponseBody
    @RequestMapping("sysRole/add")
    public Object roleAdd(@RequestBody SysRole sysRole) {
        try {
            log.info("-------------添加角色-------------");
            return sysRoleService.insert(sysRole);
        } catch (Exception e) {
            log.error("添加角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 删除角色
     */
    @ResponseBody
    @RequestMapping("role/del")
    public Object roleDel(@RequestBody String jsonBody) {
        try {
            log.info("-------------删除角色-------------");
            if (sysRoleService.deleteById(JSONObject.parseObject(jsonBody).getInteger("id"))) {
                return Response.success("删除成功");
            }
            return Response.error("删除失败");
        } catch (Exception e) {
            log.error("添加角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有角色
     */
    @ResponseBody
    @RequestMapping("roles")
    public Object roles() {
        try {
            log.info("-------------获取所有角色-------------");
            return Response.success(sysRoleService.queryAll());
        } catch (Exception e) {
            log.error("获取所有角色失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/roleList")
    public LayuiVO roleList(Integer page, Integer limit) {
        try {
            log.info("-------------查询所有角色信息-------------");
            return sysRoleService.queryAllByLimit((page - 1) * limit, limit);
        } catch (Exception e) {
            log.info("查询所有角色信息失败:{}", e.getMessage());
            return null;
        }
    }
}
