package com.lingnan.sas.mapper;

import com.lingnan.sas.core.entity.Finance;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Finance)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-11 15:59:58
 */
public interface FinanceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Finance queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Finance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param finance 实例对象
     * @return 对象列表
     */
    List<Finance> queryAll(Finance finance);

    /**
     * 新增数据
     *
     * @param finance 实例对象
     * @return 影响行数
     */
    int insert(Finance finance);

    /**
     * 修改数据
     *
     * @param finance 实例对象
     * @return 影响行数
     */
    int update(Finance finance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}