package com.lingnan.sas.client.controller;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.service.NewsInfoService;
import com.lingnan.sas.core.entity.ClockVO;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.entity.SearchMessageByIdVO;
import com.lingnan.sas.core.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/4/1.
 * @time 18:41
 */
@Slf4j
@RestController
@RequestMapping("/newInfo")
//添加@api swagger才会扫描类下面的方法
@Api("公告模块接口")
public class NewsInfoController {
    @Autowired
    private NewsInfoService newsInfoService;


    @PostMapping("/queryNewsByPage")
    @ApiOperation("分页查找公告信息")
    public R queryNewsByPage(@Valid @RequestBody ClockVO clock) {
        log.info("----------------分页查找公告信息-------------------");
        int page = clock.getPage();
        int length = clock.getLength();
        int start = (page - 1) * length;
        List<Newsinfo> result = newsInfoService.queryNewsByPage(start, length);
        return R.ok().put("result", result);
    }

    @PostMapping("/queryNewsById")
    @ApiOperation("ID查找公告信息")
    public R queryNewsById(@Valid @RequestBody SearchMessageByIdVO clock) {
        log.info("----------------ID查找公告信息-------------------");
        int id = Integer.parseInt(clock.getId());
       Newsinfo result = newsInfoService.queryNewsById(id);
        return R.ok().put("result", result);
    }
}
