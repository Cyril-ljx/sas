package com.lingnan.sas.client.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lingnan.sas.client.service.TeacherService;
import com.lingnan.sas.client.service.TeacherService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.Teacher;
import com.lingnan.sas.mapper.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;

/**
 * 教师(Teacher)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("teacherService")
@Scope("prototype")
public class TeacherServiceImpl implements TeacherService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private TeacherDao teacherDao;

    @Autowired
    private MessageTask messageTask;

    private String getOpenId(String code) {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        HashMap map = new HashMap();
        map.put("appid", appId);
        map.put("secret", appSecret);
        map.put("js_code", code);
        map.put("grant_type", "authorization_code");
        String response = HttpUtil.post(url, map);
        JSONObject json = JSONUtil.parseObj(response);
        String openId = json.getStr("openid");
        if (openId == null || openId.length() == 0) {
            throw new RuntimeException("临时登陆凭证错误");
        }
        return openId;
    }



    @Override
    public int bindUser(String code, String nickname, String photo) {
        return 0;
    }

    @Override
    public Teacher searchMessage(String username) {
        return teacherDao.queryById(Integer.parseInt(username));
    }

    @Override
    public int update(Teacher teacher) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统");
        entity.setTitle("个人信息修改");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("恭喜您已成功修改个人信息");
        entity.setSendTime(new Date());
        messageTask.sendAsync(teacher.getTid() + "", entity);
        messageTask.receive(teacher.getTid()+"");
        return teacherDao.update(teacher);
    }

    @Override
    public Teacher queryById(String username) {
        Integer id = Integer.valueOf(username);
        return teacherDao.queryById(id);
    }
}