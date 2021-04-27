package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.LoginLogService;
import com.lingnan.sas.core.entity.LoginLog;
import com.lingnan.sas.core.util.CommonResponse;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.LoginLogDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录日志(LoginLog)表服务实现类
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogDao loginLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LoginLog queryById(Integer id) {
        return this.loginLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<LoginLog> queryAllByLimit(int offset, int limit) {
        return this.loginLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog insert(LoginLog loginLog) {
        this.loginLogDao.insert(loginLog);
        return loginLog;
    }

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    @Override
    public LoginLog update(LoginLog loginLog) {
        this.loginLogDao.update(loginLog);
        return this.queryById(loginLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    @Override
    public Response batchDelete(String ids) {
        String[] id = ids.split(",");
        List<Integer> allId = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            allId.add(Integer.valueOf(id[i]));
        }
        boolean result = loginLogDao.batchDelete(allId) > 0;
        if (result) {
            return Response.success("删除成功");
        }
        return Response.error("删除失败");
    }

    @Override
    public CommonResponse deleteAll() {
        boolean result = this.loginLogDao.deleteAll() > 0;
        if (result) {
            return CommonResponse.success("删除成功");
        }
        return CommonResponse.error("删除失败");
    }

    @Override
    public Integer queryAdminCount() {
        return this.loginLogDao.queryAdminCount();
    }

    @Override
    public List<LoginLog> queryAdminList(Integer offset, Integer limit) {
        return this.loginLogDao.queryAdminList(offset, limit);
    }
}