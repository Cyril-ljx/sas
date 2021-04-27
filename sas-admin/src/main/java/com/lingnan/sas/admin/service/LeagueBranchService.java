package com.lingnan.sas.admin.service;

import com.lingnan.sas.core.entity.LeagueBranch;

import java.util.List;

/**
 * (LeagueBranch)表服务接口
 *
 * @author makejava
 * @since 2021-03-11 16:26:40
 */
public interface LeagueBranchService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LeagueBranch queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LeagueBranch> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param leagueBranch 实例对象
     * @return 实例对象
     */
    LeagueBranch insert(LeagueBranch leagueBranch);

    /**
     * 修改数据
     *
     * @param leagueBranch 实例对象
     * @return 实例对象
     */
    LeagueBranch update(LeagueBranch leagueBranch);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}