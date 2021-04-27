package com.lingnan.sas.admin.service;



import com.lingnan.sas.core.entity.LoginLog;
import com.lingnan.sas.core.util.CommonResponse;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 登录日志(LoginLog)表服务接口
 *
 * @author makejava
 * @since 2021-02-16 12:50:35
 */
public interface LoginLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LoginLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<LoginLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    LoginLog insert(LoginLog loginLog);

    /**
     * 修改数据
     *
     * @param loginLog 实例对象
     * @return 实例对象
     */
    LoginLog update(LoginLog loginLog);

    /**
     * 通过主键删除数据
     *
     * @param ids 主键
     * @return 是否成功
     */
    Response batchDelete(String ids);

    /**
     * 删除全部数据
     * @return 是否成功
     */
    CommonResponse deleteAll();

    Integer queryAdminCount();

    List<LoginLog> queryAdminList(Integer offset, Integer limit);
}