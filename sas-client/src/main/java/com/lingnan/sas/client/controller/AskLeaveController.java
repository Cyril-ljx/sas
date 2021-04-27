package com.lingnan.sas.client.controller;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.service.AskLeaveService;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.core.entity.ClockVO;
import com.lingnan.sas.core.entity.AskLeave;
import com.lingnan.sas.core.entity.SearchMessageByIdVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/4/1.
 * @time 18:41
 */
@Slf4j
@RestController
@RequestMapping("/askLeave")
//添加@api swagger才会扫描类下面的方法
@Api("请假模块接口")
public class AskLeaveController {

    @Autowired
    private AskLeaveService askLeaveService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/queryAllById")
    @ApiOperation("分页查找个人正在请假信息")
    public R queryAllById(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------分页查找个人正在请假信息-------------------");
        int userId = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = askLeaveService.queryAllById(userId, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/queryAllOverById")
    @ApiOperation("分页查找个人已结束请假信息")
    public R queryAllOverById(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------分页查找个人已结束请假信息-------------------");
        int userId = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = askLeaveService.queryAllOverById(userId, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/queryAskLeaveById")
    @ApiOperation("ID查找请假信息")
    public R queryAskLeaveById(@Valid @RequestBody SearchMessageByIdVO clock) {
        log.info("----------------ID查找请假信息-------------------");
        int id = Integer.parseInt(clock.getId());
        AskLeave result = askLeaveService.queryAskLeaveById(id);
        return R.ok().put("result", result);
    }

    @PostMapping("/deleteAskLeaveById")
    @ApiOperation("删除请假信息")
    public R deleteAskLeaveById(@Valid @RequestBody SearchMessageByIdVO searchMessageByIdVO) {
        log.info("----------------删除请假信息-------------------");
        int id = Integer.parseInt(searchMessageByIdVO.getId());
        int result = askLeaveService.deleteById(id);
        return R.ok().put("result", result);
    }

    @PostMapping("/updateAskLeave")
    @ApiOperation("更新请假信息")
    public R updateAskLeave(@Valid @RequestBody AskLeave askLeave) {
        log.info("----------------更新请假信息-------------------");
        int result = askLeaveService.updateAskLeave(askLeave);
        return R.ok().put("result", result);
    }

    @PostMapping("/insertAskLeave")
    @ApiOperation("添加请假信息")
    public R insertAskLeave(@Valid @RequestBody AskLeave askLeave, @RequestHeader("token") String token) {
        log.info("----------------添加请假信息-------------------");
        int userId = jwtUtil.getUserId(token);
        askLeave.setSid(userId);
       return askLeaveService.insert(askLeave);
    }

    @PostMapping("/searchAskLeave")
    @ApiOperation("根据教师id查找未审批请假的")
    public R searchAskLeave(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------根据教师id查找未审批请假的-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = askLeaveService.searchAskLeave(username, start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/searchApprove")
    @ApiOperation("根据教师id查找未审批请假的")
    public R searchApprove(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------根据教师id查找未审批请假的-------------------");
        int id = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        String username = sysUserService.queryById(id).getLoginName();
        List<HashMap> result = askLeaveService.searchApprove(username, start, length);
        return R.ok().put("result", result);
    }
}
