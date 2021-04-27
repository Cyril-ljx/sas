package com.lingnan.sas.client.controller;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.service.DeliverService;
import com.lingnan.sas.client.service.NewsInfoService;
import com.lingnan.sas.core.entity.ClockVO;
import com.lingnan.sas.core.entity.Deliver;
import com.lingnan.sas.core.entity.Newsinfo;
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
@RequestMapping("/deliver")
//添加@api swagger才会扫描类下面的方法
@Api("公告模块接口")
public class    DeliverController {

    @Autowired
    private DeliverService deliverService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/queryAllById")
    @ApiOperation("分页查找个人正在投递信息")
    public R queryAllById(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------分页查找个人正在投递信息-------------------");
        int userId = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = deliverService.queryAllById(userId,start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/queryAllOverById")
    @ApiOperation("分页查找个人已结束投递信息")
    public R queryAllOverById(@Valid @RequestBody ClockVO clock, @RequestHeader("token") String token) {
        log.info("----------------分页查找个人已结束投递信息-------------------");
        int userId = jwtUtil.getUserId(token);
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<HashMap> result = deliverService.queryAllOverById(userId,start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/queryDeliverById")
    @ApiOperation("ID查找投递信息")
    public R queryDeliverById(@Valid @RequestBody SearchMessageByIdVO clock) {
        log.info("----------------ID查找投递信息-------------------");
        int id = Integer.parseInt(clock.getId());
       Deliver result = deliverService.queryDeliverById(id);
        return R.ok().put("result", result);
    }

    @PostMapping("/deleteDeliverById")
    @ApiOperation("更新投递信息")
    public R deleteDeliverById(@Valid @RequestBody SearchMessageByIdVO searchMessageByIdVO) {
        log.info("----------------更新投递信息-------------------");
        int id = Integer.parseInt(searchMessageByIdVO.getId());
        int result = deliverService.deleteById(id);
        return R.ok().put("result", result);
    }

    @PostMapping("/updateDeliver")
    @ApiOperation("更新投递信息")
    public R updateDeliver(@Valid @RequestBody Deliver deliver) {
        log.info("----------------更新投递信息-------------------");
        int result = deliverService.updateDeliver(deliver);
        return R.ok().put("result", result);
    }

    @PostMapping("/insertDeliver")
    @ApiOperation("添加投递信息")
    public R insertDeliver(@Valid @RequestBody Deliver deliver, @RequestHeader("token") String token) {
        log.info("----------------更新投递信息-------------------");
        int userId = jwtUtil.getUserId(token);
        deliver.setSuid(userId);
        int result = deliverService.insert(deliver);
        return R.ok().put("result", result);
    }
}
