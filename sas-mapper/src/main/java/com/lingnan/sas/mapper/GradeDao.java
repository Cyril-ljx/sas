package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.Major;
import com.lingnan.sas.core.entity.SchoolManageVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 年级(Grade)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:19
 */
public interface GradeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param gid 主键
     * @return 实例对象
     */
    Grade queryById(Integer gid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<SchoolManageVO> queryAllByLimit(SchoolManageVO schoolManageVO);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param schoolManageVO 实例对象
     * @return 对象列表
     */
    List<SchoolManageVO> queryAll(SchoolManageVO schoolManageVO);

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 影响行数
     */
    int insert(Grade grade);

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 影响行数
     */
    int update(Grade grade);

    /**
     * 通过主键删除数据
     *
     * @param gid 主键
     * @return 影响行数
     */
    int deleteById(Integer gid);

    Integer queryGradeCount();
    //条件查询grade
    List<SchoolManageVO> tjSelGrade(SchoolManageVO schoolManageVO);

    List<SchoolManageVO> jlSelGrade(SchoolManageVO schoolManageVO);

    int checkOneGrade(Grade grade);

    /**
     * 分页查询
     *
     * @param offset 开始位置
     * @param limit 每页搜索数
     * @return 实例对象
     */
    List<Grade> queryGradeByPage(@Param("mid")int mid , @Param("offset") int offset, @Param("limit") int limit);

    //添加年级
    int addOneGrade(Grade grade);
}