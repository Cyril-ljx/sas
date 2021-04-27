package com.lingnan.sas.client.controller;


import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.config.shiro.JwtUtil;
import com.lingnan.sas.client.service.MessageService;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/message")
@Api("消息模块网络接口")
public class MessageController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageTask messageTask;

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/searchMessageByPage")
    @ApiOperation("获取分页消息列表")
    public R searchMessageByPage(@Valid @RequestBody SearchMessageByPageVO form, @RequestHeader("token") String token) {
        int userId = jwtUtil.getUserId(token);
        int page = form.getPage();
        int length = form.getLength();
        long start = (page - 1) * length;
        List<HashMap> list = messageService.searchMessageByPage(userId, start, length);
        return R.ok().put("result", list);
    }

    @PostMapping("/searchMessageById")
    @ApiOperation("根据ID查询消息")
    public R searchMessageById(@Valid @RequestBody SearchMessageByIdVO form) {
        HashMap map = messageService.searchMessageById(form.getId());
        return R.ok().put("result", map);
    }

    @PostMapping("/updateUnreadMessage")
    @ApiOperation("未读消息更新成已读消息")
    public R updateUnreadMessage(@Valid @RequestBody UpdateUnreadMessageVO form) {
        long rows = messageService.updateUnreadMessage(form.getId());
        return R.ok().put("result", rows == 1 ? true : false);
    }

    @PostMapping("/deleteMessageRefById")
    @ApiOperation("删除消息")
    public R deleteMessageRefById(@Valid @RequestBody DeleteMessageRefByIdVO form) {
        long rows = messageService.deleteMessageRefById(form.getId());
        return R.ok().put("result", rows == 1 ? true : false);
    }

    @GetMapping("/refreshMessage")
    @ApiOperation("刷新用户消息")
    public R refreshMessage(@RequestHeader("token") String token){
        log.info("------------刷新消息---------------");
        int userId=jwtUtil.getUserId(token);
        //异步接收消息
        messageTask.receiveAsync(userId+"");
        //查询接收了多少条消息
        long lastRows=messageService.searchLastCount(userId);
        //查询未读数据
        long unreadRows=messageService.searchUnreadCount(userId);
        log.info(String.valueOf(unreadRows));
        return R.ok().put("lastRows",lastRows).put("unreadRows",unreadRows);
    }

    @PostMapping("/publishMessage")
    @ApiOperation("教师发布公告")
    public R publishMessage(@Valid @RequestBody MessageEntity messageEntity,@RequestHeader("token") String token) {
        int id = jwtUtil.getUserId(token);
        SysUser sysUser = sysUserService.queryById(id);
        String username =sysUser.getLoginName();
        String nickName = sysUser.getNickName();
        List<SysUser> ids = sysUserService.queryId(username);
        //往ref保存数据
        List<MessageRefEntity> entitys = new LinkedList<MessageRefEntity>();
        MessageRefEntity messageRefEntity = new MessageRefEntity();
        messageRefEntity.setReceiverId(id);
        entitys.add(messageRefEntity);
        for(SysUser s : ids){
            MessageRefEntity entity = new MessageRefEntity();
            entity.setReceiverId(s.getId());
            entitys.add(entity);
        }
        messageEntity.setSenderId(id);
        messageEntity.setSenderName(nickName);
        int result = messageService.publishMessage(messageEntity,entitys);
        return R.ok().put("result",result);
    }

}
