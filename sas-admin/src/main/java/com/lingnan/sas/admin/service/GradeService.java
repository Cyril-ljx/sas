package com.lingnan.sas.admin.service;

import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 年级(Grade)表服务接口
 *
 * @author makejava
 * @since 2021-03-15 16:48:11
 */
public interface GradeService {

    /**
     * 通过ID查询单条数据
     *
     * @param gid 主键
     * @return 实例对象
     */
    Grade queryById(Integer gid);

    /**
     * 查询多条数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO, int page, int limit);

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    Grade insert(Grade grade);

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    Grade update(Grade grade);

    /**
     * 通过主键删除数据
     *
     * @param gid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gid);

    //查询全部班级
    List<SchoolManageVO> queryAll(SchoolManageVO schoolManageVO);

    //条件查询grade
    List<SchoolManageVO> tjSelGrade(SchoolManageVO schoolManageVO);

    //级联查询年级
    List<SchoolManageVO> jlSelGrade(SchoolManageVO schoolManageVO);

    //添加年级
    Response addOneGrade(Grade grade);
}