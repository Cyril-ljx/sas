package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.PoliticsType;

import java.util.List;

/**
 * 团组织类型(PoliticsType)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
public interface PoliticsTypeService {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    PoliticsType queryById(Integer pid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PoliticsType> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param politicsType 实例对象
     * @return 实例对象
     */
    PoliticsType insert(PoliticsType politicsType);

    /**
     * 修改数据
     *
     * @param politicsType 实例对象
     * @return 实例对象
     */
    PoliticsType update(PoliticsType politicsType);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer pid);

    Object queryAll();
}