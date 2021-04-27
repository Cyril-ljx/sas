package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.PoliticsTypeService;
import com.lingnan.sas.core.entity.PoliticsType;
import com.lingnan.sas.mapper.PoliticsTypeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 团组织类型(PoliticsType)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
@Service("politicsTypeService")
public class PoliticsTypeServiceImpl implements PoliticsTypeService {
    @Resource
    private PoliticsTypeDao politicsTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    @Override
    public PoliticsType queryById(Integer pid) {
        return this.politicsTypeDao.queryById(pid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PoliticsType> queryAllByLimit(int offset, int limit) {
        return this.politicsTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param politicsType 实例对象
     * @return 实例对象
     */
    @Override
    public PoliticsType insert(PoliticsType politicsType) {
        this.politicsTypeDao.insert(politicsType);
        return politicsType;
    }

    /**
     * 修改数据
     *
     * @param politicsType 实例对象
     * @return 实例对象
     */
    @Override
    public PoliticsType update(PoliticsType politicsType) {
        this.politicsTypeDao.update(politicsType);
        return this.queryById(politicsType.getPid());
    }

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer pid) {
        return this.politicsTypeDao.deleteById(pid) > 0;
    }

    @Override
    public Object queryAll() {
        return politicsTypeDao.queryAll(null);
    }
}