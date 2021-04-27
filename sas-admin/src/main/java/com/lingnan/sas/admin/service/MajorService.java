package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.Major;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 专业(Major)表服务接口
 *
 * @author makejava
 * @since 2021-03-15 16:48:10
 */
public interface MajorService {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Major queryById(Integer mid);

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO,int page, int limit);

    /**
     * 新增数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    Major insert(Major major);

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    Major update(Major major);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer mid);

    Object queryAll();

    //条件查询
    List<SchoolManageVO> tjSelMajor(SchoolManageVO schoolManageVO);

    //级联查询
    List<SchoolManageVO> jlSelMajor(SchoolManageVO schoolManageVO);


    Response addOneMajor(Major major);
}