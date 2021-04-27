package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.PoliticsType;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 团组织类型(PoliticsType)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:19
 */
public interface PoliticsTypeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pid 主键
     * @return 实例对象
     */
    PoliticsType queryById(Integer pid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PoliticsType> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param politicsType 实例对象
     * @return 对象列表
     */
    List<PoliticsType> queryAll(PoliticsType politicsType);

    /**
     * 新增数据
     *
     * @param politicsType 实例对象
     * @return 影响行数
     */
    int insert(PoliticsType politicsType);

    /**
     * 修改数据
     *
     * @param politicsType 实例对象
     * @return 影响行数
     */
    int update(PoliticsType politicsType);

    /**
     * 通过主键删除数据
     *
     * @param pid 主键
     * @return 影响行数
     */
    int deleteById(Integer pid);

}