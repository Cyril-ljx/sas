package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.AskLeave;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (AskLeave)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-07 23:18:52
 */
public interface AskLeaveDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AskLeave queryById(Integer id);

    /**
     * 通过学生ID查询正在请假的学生
     *
     * @param sid 主键
     * @return 实例对象
     */
    AskLeave queryBysId(@Param("sid") Integer sid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<AskLeave> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param askLeave 实例对象
     * @return 对象列表
     */
    List<AskLeave> queryAll(AskLeave askLeave);

    /**
     * 新增数据
     *
     * @param askLeave 实例对象
     * @return 影响行数
     */
    int insert(AskLeave askLeave);

    /**
     * 修改数据
     *
     * @param askLeave 实例对象
     * @return 影响行数
     */
    int update(AskLeave askLeave);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /*
     * 查找请假结束的
     * */
    ArrayList<HashMap> queryAllOverById(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);

    /*根据教师id查找正在请假的*/
    ArrayList<HashMap> searchAskLeave(@Param("tid") int tid, @Param("offset") int offset, @Param("limit") int limit);

    /*根据教师id查找已审批请假的*/
    ArrayList<HashMap> searchApprove(@Param("tid") int tid, @Param("offset") int offset, @Param("limit") int limit);

    /*
     * 查找正在请假的
     * */
    ArrayList<HashMap> queryAllById(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);
}