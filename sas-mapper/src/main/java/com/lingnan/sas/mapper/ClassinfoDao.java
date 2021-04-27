package com.lingnan.sas.mapper;

import com.lingnan.sas.core.entity.Classinfo;
import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.SchoolManageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 班级信息(Classinfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:19
 */
@Mapper
public interface ClassinfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param classid 主键
     * @return 实例对象
     */
    Classinfo queryById(Integer classid);

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
     * @param classinfo 实例对象
     * @return 对象列表
     */
    List<Classinfo> queryAll(Classinfo classinfo);

    /**
     * 新增数据
     *
     * @param classinfo 实例对象
     * @return 影响行数
     */
    int insert(Classinfo classinfo);

    /**
     * 修改数据
     *
     * @param classinfo 实例对象
     * @return 影响行数
     */
    int update(Classinfo classinfo);

    /**
     * 通过主键删除数据
     *
     * @param classid 主键
     * @return 影响行数
     */
    int deleteById(Integer classid);


    Integer queryClassCount();

    /**
     * 条件查询
     */
    //条件查询classname
    List<SchoolManageVO> tjSelClass(SchoolManageVO schoolManageVO);

    int checkOneClass(Classinfo classInfo);

    int addOneClass(Classinfo classInfo);

    /**
     * 分页查询
     *
     * @param offset 开始位置
     * @param limit 每页搜索数
     * @return 实例对象
     */
    List<Classinfo> queryClassByPage(@Param("gid")int gid , @Param("offset") int offset, @Param("limit") int limit);
}