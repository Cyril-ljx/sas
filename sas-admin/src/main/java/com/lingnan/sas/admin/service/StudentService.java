package com.lingnan.sas.admin.service;


import com.lingnan.sas.core.entity.*;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;

import java.util.List;

/**
 * 学生(Student)表服务接口
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
public interface StudentService {

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    Student queryById(Integer sid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    LayuiVO queryAllByLimit(int offset, int limit,String sname,Integer sid, Integer classid);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Student insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Response update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer sid);

    Integer queryStudentCount(String sname,Integer sid, Integer classid);

    Object queryAll();

    //  查询系部
     List<Department> selDepartment();

    //  根据系部查询专业
     List<Major> selMajor(Integer did);
    //  根据专业查询年级
    List<Grade> selGrade(Integer mid);
    //  根据年级查询班级
    List<Classinfo> selClassinfo(Integer gid);

    /**
     * 增加学生
     *
     * @param student 实例对象
     * @return 实例对象
     */
    Response add(Student student);
}