package com.lingnan.sas.client.service;


import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.HashMap;
import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(int offset, int limit);


//    LayuiVO queryAdminList(Integer offset, Integer limit);
    /**
     * 新增数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
//    Response insert(SysUser User);

    /**
     * 修改数据
     *
     * @param User 实例对象
     * @return 实例对象
     */
    Integer update(SysUser User);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    String getRole(String loginName);

    SysUser findUserByLoginName(String username);

    Integer queryAdminCount(int sid);

    SysUser queryByUserName(String username);

    Integer login(SysUser user);

    HashMap searchUserSummary(String username);

    List<SysUser> queryId(String username);

}