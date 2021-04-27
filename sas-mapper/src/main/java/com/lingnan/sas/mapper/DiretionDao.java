package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Diretion;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * (Diretion)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 15:59:58
 */
public interface DiretionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Diretion queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Diretion> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param diretion 实例对象
     * @return 对象列表
     */
    List<Diretion> queryAll(Diretion diretion);

    /**
     * 新增数据
     *
     * @param diretion 实例对象
     * @return 影响行数
     */
    Integer insert(Diretion diretion);

    /**
     * 修改数据
     *
     * @param diretion 实例对象
     * @return 影响行数
     */
    int update(Diretion diretion);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Integer queryDiretionCount();

    /**
     * 查找未打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotDirection(@Param("cid") Integer cid,@Param("offset")Integer offset,@Param("limit")Integer limit);

    /**
     * 查找已打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchDirection(@Param("cid") Integer cid,@Param("offset")Integer offset,@Param("limit")Integer limit);

    /**
     * 根据班级查找假期去向已打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchDirectionNum(@Param("tid") Integer tid, @Param("offset")Integer offset, @Param("limit")Integer limit);

    /**
     * 根据班级查找假期去向未打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotDirectionNum(@Param("tid") Integer tid, @Param("offset")Integer offset, @Param("limit")Integer limit);
}