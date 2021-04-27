package com.lingnan.sas.admin.service.impl;


import cn.hutool.core.util.IdUtil;
import com.lingnan.sas.admin.service.SysUserService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.SysUserDao;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * (SysUser)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserDao sysUserDao;


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
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<SysUser> queryAllByLimit(int offset, int limit) {
        return this.sysUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public Response insert(SysUser sysUser) {
        SysUser result = sysUserDao.queryByUserName(sysUser.getLoginName());
        if (result != null) {
            return Response.error("账号已存在");
        }
        sysUser.setSalt(ShiroUtil.createSalt());
        sysUser.setPassword(ShiroUtil.salt(sysUser.getPassword(), sysUser.getSalt()));
        sysUser.setLocked(false);
        int result1 = sysUserDao.insert(sysUser);
        if (result1 >0) {
            return Response.success("添加成功");
        }
        return Response.error("添加失败");
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public Response update(SysUser sysUser) {
        if (!StringUtils.isEmpty(sysUser.getPassword())) {
            sysUser.setPassword(ShiroUtil.salt(sysUser.getPassword(), sysUser.getSalt()));
        }

        if (sysUserDao.update(sysUser) >0) {
            return Response.success("修改成功");
        }
        return Response.error("修改失败");
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

    @Override
    public LayuiVO queryAdminList(Integer offset, Integer limit) {
        int sid = ShiroUtil.getUserId();
        List<SysUser> sysUsers = sysUserDao.queryAdminList(sid, offset, limit);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(sysUserDao.queryAdminCount(sid));//总数
        layData.setData(sysUsers);
        return layData;
    }

    @Override
    public Integer queryAdminCount(int sid) {
        return this.sysUserDao.queryAdminCount(sid);
    }

    @Override
    public SysUser queryByUserName(String username) {
        return this.sysUserDao.queryByUserName(username);
    }
}