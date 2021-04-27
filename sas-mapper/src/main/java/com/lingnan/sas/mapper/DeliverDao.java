package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Deliver;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (Deliver)表数据库访问层
 *
 * @author makejava
 * @since 2021-04-02 01:42:21
 */
public interface DeliverDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Deliver queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Deliver> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询进行中的投递信息
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    ArrayList<HashMap> queryAllById(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询进行中的投递信息
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    ArrayList<HashMap> queryAllOverById(@Param("id") int id, @Param("offset") int offset, @Param("limit") int limit);



    /**
     * 新增数据
     *
     * @param deliver 实例对象
     * @return 影响行数
     */
    int insert(Deliver deliver);

    /**
     * 修改数据
     *
     * @param deliver 实例对象
     * @return 影响行数
     */
    int update(Deliver deliver);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 通过主键删除数据
     *
     * @return 影响行数
     */
    int deleteAll();

    /**
     * 查找最新的日期
     *
     * @return 影响行数
     */
    String selectLastData();


}