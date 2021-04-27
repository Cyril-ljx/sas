package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.Department;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 系(Department)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:06:57
 */
public interface DepartmentService {

    /**
     * 通过ID查询单条数据
     *
     * @param did 主键
     * @return 实例对象
     */
    Department queryById(Integer did);

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO, int page, int limit);

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department insert(Department department);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    Department update(Department department);

    /**
     * 通过主键删除数据
     *
     * @param did 主键
     * @return 是否成功
     */
    boolean deleteById(Integer did);

    Object queryAll();

    List<SchoolManageVO> tjSelDpm(SchoolManageVO schoolManageVO);

    int checkOneDpm(Department department);

    Response addOneDpm(Department department);
}