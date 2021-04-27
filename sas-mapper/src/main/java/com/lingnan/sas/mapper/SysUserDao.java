package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:14
 */
public interface SysUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUser queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SysUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    List<SysUser> queryAdminList(@Param("sid") int sid, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysUser 实例对象
     * @return 对象列表
     */
    List<SysUser> queryAll(SysUser sysUser);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tid 教师id
     * @return 对象列表
     */
    List<SysUser> queryId(Integer tid);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUser sysUser);

    /*
    * 更新微信登录的数据
    * */
    int updateWx(SysUser sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUser sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    String getRole( @Param("loginName") String loginName);

    SysUser findUserByLoginName( @Param("username")String username);

    Integer queryAdminCount(int sid);

    SysUser queryByUserName(@Param("username") String username);

    Integer searchId(@Param("account") String account,@Param("pwd") String pwd);

    /*查找用户信息*/
    HashMap searchUserSummary(String username);

}