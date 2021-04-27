package com.lingnan.sas.client.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lingnan.sas.client.service.StudentService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.core.entity.*;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.StudentDao;
import org.apache.velocity.runtime.directive.Parse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 学生(Student)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("studentService")
@Scope("prototype")
public class StudentServiceImpl implements StudentService {

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Resource
    private StudentDao studentDao;

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
    public Student searchMessage(String username) {
        return studentDao.queryById(Integer.parseInt(username));
    }

    @Override
    public int update(Student student) {
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统");
        entity.setTitle("个人信息修改");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("恭喜您已成功修改个人信息");
        entity.setSendTime(new Date());
        messageTask.sendAsync(student.getStuid() + "", entity);
        messageTask.receive(student.getStuid()+"");
        return studentDao.update(student);
    }

    @Override
    public Student queryById(String username) {
        Integer id = Integer.valueOf(username);
        return studentDao.queryById(id);
    }
}