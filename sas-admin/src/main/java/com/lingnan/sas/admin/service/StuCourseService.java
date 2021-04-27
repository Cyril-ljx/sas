package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.StuCourse;

import java.util.List;

/**
 * (StuCourse)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
public interface StuCourseService {

    /**
     * 通过ID查询单条数据
     *
     * @param scid 主键
     * @return 实例对象
     */
    StuCourse queryById(Integer scid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StuCourse> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param stuCourse 实例对象
     * @return 实例对象
     */
    StuCourse insert(StuCourse stuCourse);

    /**
     * 修改数据
     *
     * @param stuCourse 实例对象
     * @return 实例对象
     */
    StuCourse update(StuCourse stuCourse);

    /**
     * 通过主键删除数据
     *
     * @param scid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer scid);

}