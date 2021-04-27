package com.lingnan.sas.client.controller;



import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.controller.form.TestSayHelloForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/newInfo")
//添加@api swagger才会扫描类下面的方法
@Api("测试Web接口")
public class TestController {
    @PostMapping("/sayHello")
    @ApiOperation("最简单的测试方法")
    public R sayHello(@Valid @RequestBody TestSayHelloForm form) {
        return R.ok().put("message", "Hello,"+form.getName());
    }

    @GetMapping("/hi")
    public R hi(){
        return R.ok();
    }

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    @RequiresRoles("admin")
    public R addUser(){
        return R.ok("用户添加成功");
    }


}
