package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.StuCourse;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (StuCourse)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:19
 */
public interface StuCourseDao {

    /**
     * 通过ID查询单条数据
     *
     * @param scid 主键
     * @return 实例对象
     */
    StuCourse queryById(Integer scid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<StuCourse> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param stuCourse 实例对象
     * @return 对象列表
     */
    List<StuCourse> queryAll(StuCourse stuCourse);

    /**
     * 新增数据
     *
     * @param stuCourse 实例对象
     * @return 影响行数
     */
    int insert(StuCourse stuCourse);

    /**
     * 修改数据
     *
     * @param stuCourse 实例对象
     * @return 影响行数
     */
    int update(StuCourse stuCourse);

    /**
     * 通过主键删除数据
     *
     * @param scid 主键
     * @return 影响行数
     */
    int deleteById(Integer scid);

}