package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.LeagueBranchService;
import com.lingnan.sas.core.entity.LeagueBranch;
import com.lingnan.sas.mapper.LeagueBranchDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (LeagueBranch)表服务实现类
 *
 * @author makejava
 * @since 2021-03-11 16:26:40
 */
@Service("leagueBranchService")
public class LeagueBranchServiceImpl implements LeagueBranchService {
    @Resource
    private LeagueBranchDao leagueBranchDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LeagueBranch queryById(Integer id) {
        return this.leagueBranchDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<LeagueBranch> queryAllByLimit(int offset, int limit) {
        return this.leagueBranchDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param leagueBranch 实例对象
     * @return 实例对象
     */
    @Override
    public LeagueBranch insert(LeagueBranch leagueBranch) {
        this.leagueBranchDao.insert(leagueBranch);
        return leagueBranch;
    }

    /**
     * 修改数据
     *
     * @param leagueBranch 实例对象
     * @return 实例对象
     */
    @Override
    public LeagueBranch update(LeagueBranch leagueBranch) {
        this.leagueBranchDao.update(leagueBranch);
        return this.queryById(leagueBranch.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.leagueBranchDao.deleteById(id) > 0;
    }
}