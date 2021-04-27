package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.Student;
import com.lingnan.sas.core.entity.Teacher;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 教师表(Teacher)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:07:03
 */
public interface TeacherService {

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    Teacher queryById(Integer tid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(int offset, int limit, String tname, Integer tid, Integer gid);

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    Teacher insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    Response update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer tid);

    /**
     * 增加教师
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    Response add(Teacher teacher);
}