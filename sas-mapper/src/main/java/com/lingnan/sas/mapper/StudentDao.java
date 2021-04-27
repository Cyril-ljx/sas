package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生(Student)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:18
 */
@Mapper
public interface StudentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    Student queryById(Integer sid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Student> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit,@Param("sname") String sname,@Param("sid")Integer sid,@Param("classid") Integer classid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryAll(Student student);

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 影响行数
     */
    int deleteById(Integer sid);

    Integer queryStudentCount(@Param("sname")String sname,@Param("sid")Integer sid,@Param("classid") Integer classid);

    //  查询系部
    List<Department> selDepartment();

    //  根据系部查询专业
    List<Major> selMajor(Integer did);

    //  根据专业查询年级
    List<Grade> selGrade(Integer mid);

    //  根据年级查询班级
    List<Classinfo> selClassinfo(Integer gid);

    Integer finfById(String stuid);

    //  班级人数+1
    Integer selecteClassinfo(Integer classid);
    //  年级人数+1
    Integer selecteGrade(Integer gid);
    //  专业人数+1
    Integer selecteMajor(Integer mid);
    //  系部人数+1
    Integer selecteDepartment(Integer did);

    /**
     * 分页查询
     *
     * @param offset 开始位置
     * @param limit 每页搜索数
     * @return 实例对象
     */
    List<Student> queryStuByPage(@Param("cid")int cid , @Param("offset") int offset, @Param("limit") int limit);
}