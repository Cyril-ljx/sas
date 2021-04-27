package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 教师表(Teacher)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 15:59:55
 */
public interface TeacherDao {

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    Teacher queryById(Integer tid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Teacher> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit,
                                  @Param("tname")String tname, @Param("tid")Integer tid,@Param("gid") Integer gid);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param teacher 实例对象
     * @return 对象列表
     */
    List<Teacher> queryAll(Teacher teacher);

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 影响行数
     */
    int deleteById(Integer tid);

    Integer queryTeacherCount(@Param("tname")String tname, @Param("tid")Integer tid,@Param("gid") Integer gid);

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 影响行数
     */
    HashMap<String,Object> searchBysId(Integer sid);
}