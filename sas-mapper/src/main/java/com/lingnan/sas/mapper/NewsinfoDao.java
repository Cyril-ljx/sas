package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Classinfo;
import com.lingnan.sas.core.entity.Newsinfo;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 公告(Newsinfo)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:18
 */
public interface NewsinfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param nid 主键
     * @return 实例对象
     */
    Newsinfo queryById(Integer nid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Newsinfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param newsinfo 实例对象
     * @return 对象列表
     */
    List<Newsinfo> queryAll(Newsinfo newsinfo);

    /**
     * 新增数据
     *
     * @param newsinfo 实例对象
     * @return 影响行数
     */
    int insert(Newsinfo newsinfo);

    /**
     * 修改数据
     *
     * @param newsinfo 实例对象
     * @return 影响行数
     */
    int update(Newsinfo newsinfo);

    /**
     * 通过主键删除数据
     *
     * @param nid 主键
     * @return 影响行数
     */
    int deleteById(Integer nid);

    Integer queryStudentCount();

    List<Newsinfo> queryNewsByPage(@Param("offset") int offset, @Param("limit") int limit);

    Newsinfo queryNewsById(@Param("id") int id);

}