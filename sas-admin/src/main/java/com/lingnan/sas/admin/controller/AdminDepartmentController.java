package com.lingnan.sas.admin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.DepartmentService;
import com.lingnan.sas.admin.service.MajorService;
import com.lingnan.sas.core.entity.Department;
import com.lingnan.sas.core.entity.Major;
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
@RequestMapping("/sas/admin/department")
public class AdminDepartmentController {

    @Resource
    private DepartmentService service;

    /**
     * 分页查询
     */
    @ResponseBody
    @RequestMapping("/list")
    public LayuiVO liveList(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        try {
            log.info("-------------查询所有院系信息-------------");
            return service.queryAllByLimit(schoolManageVO,(page - 1) * limit, limit);

        } catch (Exception e) {
            log.info("查询所有院系信息失败:{}", e.getMessage());
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
            log.info("-------------删除院系-------------");
            return Response.success(service.deleteById(JSONObject.parseObject(jsonBody).getInteger("did")));
        } catch (Exception e) {
            log.error("删除院系失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 获取所有院系
     */
    @ResponseBody
    @RequestMapping("/allDepartment")
    public Object allDepartment() {
        try {
            log.info("-------------获取所有院系信息-------------");
            return Response.success(service.queryAll());
        } catch (Exception e) {
            log.error("获取所有院系信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }
    }

    /**
     * 条件查询系部
     */
    @RequestMapping("tjseldpm")
    @ResponseBody
    public Object tjSelDpm(SchoolManageVO schoolManageVO, Integer page, Integer limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> listAll = service.tjSelDpm(schoolManageVO);
        PageInfo pageInfo = new PageInfo(listAll);
        Map<String, Object> dpmData = new HashMap<String, Object>();
        dpmData.put("code", 0);
        dpmData.put("msg", "");
        dpmData.put("count", pageInfo.getTotal());
        dpmData.put("data", pageInfo.getList());
        return dpmData;
    }

    /**
     * 添加院系
     */
    @RequestMapping("add")
    @ResponseBody
    public Object addOneDpm(@RequestBody Department department) {
        try {
            log.info("-------------添加院系信息-------------");
            return service.addOneDpm(department);
        } catch (Exception e) {
            log.error("添加院系信息失败：" + e.getMessage());
            return Response.error(e.getMessage());
        }


    }
}
