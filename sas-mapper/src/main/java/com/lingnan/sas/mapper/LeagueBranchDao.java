package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.LeagueBranch;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (LeagueBranch)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 15:59:58
 */
public interface LeagueBranchDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LeagueBranch queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<LeagueBranch> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param leagueBranch 实例对象
     * @return 对象列表
     */
    List<LeagueBranch> queryAll(LeagueBranch leagueBranch);

    /**
     * 新增数据
     *
     * @param leagueBranch 实例对象
     * @return 影响行数
     */
    int insert(LeagueBranch leagueBranch);

    /**
     * 修改数据
     *
     * @param leagueBranch 实例对象
     * @return 影响行数
     */
    int update(LeagueBranch leagueBranch);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}