package com.lingnan.sas.client.service.impl;


import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.lingnan.sas.client.Exception.ClientException;
import com.lingnan.sas.client.service.SysUserService;
import com.lingnan.sas.client.task.MessageTask;
import com.lingnan.sas.client.util.ShiroUtil;
import com.lingnan.sas.core.entity.Message;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.StudentDao;
import com.lingnan.sas.mapper.SysUserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;

    @Resource
    private StudentDao studentDao;

    @Value("${wx.app-id}")
    private String appId;

    @Value("${wx.app-secret}")
    private String appSecret;

    @Autowired
    private MessageTask messageTask;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SysUser queryById(Integer id) {
        return this.sysUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

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

/*        *
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象*/

    @Override
    public Integer update(SysUser sysUser) {
        if (!StringUtils.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword(ShiroUtil.salt(sysUser.getPassword(), sysUser.getSalt()));
            MessageEntity entity = new MessageEntity();
            entity.setSenderId(0);
            entity.setSenderName("系统");
            entity.setTitle("个人信息修改");
            entity.setUuid(IdUtil.simpleUUID());
            entity.setMsg("恭喜您已成功修改个人信息");
            entity.setSendTime(new Date());
            messageTask.sendAsync(sysUser.getId() + "", entity);
            messageTask.receive(sysUser.getId()+"");
        }

        return sysUserDao.update(sysUser) ;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.sysUserDao.deleteById(id) > 0;
    }

    @Override
    public String getRole(String loginName) {
        return sysUserDao.getRole(loginName);
    }

    @Override
    public SysUser findUserByLoginName(String username) {
        return sysUserDao.findUserByLoginName(username);
    }

/*    @Override
    public LayuiVO queryAdminList(Integer offset, Integer limit) {
        int sid = ShiroUtil.getUserId();
        List<SysUser> sysUsers = sysUserDao.queryAdminList(sid, offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(sysUserDao.queryAdminCount(sid));//总数
        layData.setData(sysUsers);
        return layData;
    }*/

    @Override
    public Integer queryAdminCount(int sid) {
        return this.sysUserDao.queryAdminCount(sid);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return this.sysUserDao.queryByUserName(username);
    }

    @Override
    public Integer login(SysUser user) {
        String account = user.getLoginName();
        SysUser sysUser = sysUserDao.findUserByLoginName(account);
        if(sysUser==null){
            throw new ClientException("帐户不存在,请重新登录");
        }
        String pwd = ShiroUtil.salt(user.getPassword(), sysUser.getSalt());
        String openId = getOpenId(user.getCode());
        user.setOpenId(openId);
        user.setCreateDate(DateUtil.getNowDate());
        Integer id = sysUserDao.searchId(account, pwd);
        if (id == null) {
            throw new ClientException("帐户不存在或密码错误,请重新输入");
        }
        user.setId(id);
        sysUserDao.updateWx(user);
        MessageEntity entity = new MessageEntity();
        entity.setSenderId(0);
        entity.setSenderName("系统");
        entity.setTitle("系统消息");
        entity.setUuid(IdUtil.simpleUUID());
        entity.setMsg("欢迎登录，请尽情使用吧。");
        entity.setSendTime(new Date());
        messageTask.sendAsync(id + "", entity);
        messageTask.receive(id+"");
        return id;
    }

    @Override
    public HashMap searchUserSummary(String username) {
        return sysUserDao.searchUserSummary(username);
    }

    @Override
    public List<SysUser> queryId(String username) {
        Integer tid = Integer.valueOf(username);
        return sysUserDao.queryId(tid);
    }


}