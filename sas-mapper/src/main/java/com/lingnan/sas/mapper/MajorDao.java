package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Department;
import com.lingnan.sas.core.entity.Major;
import com.lingnan.sas.core.entity.SchoolManageVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 专业(Major)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:18
 */
public interface MajorDao {

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    Major queryById(Integer mid);

    /**
     * 分页查询
     *
     * @param offset 开始位置
     * @param limit 每页搜索数
     * @return 实例对象
     */
    List<Major> queryMajByPage(@Param("did")int did , @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询指定行数据
     *
     * @param schoolManageVO
     * @return 对象列表
     */
    List<SchoolManageVO> queryAllByLimit(SchoolManageVO schoolManageVO);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param major 实例对象
     * @return 对象列表
     */
    List<Major> queryAll(Major major);

    /**
     * 新增数据
     *
     * @param major 实例对象
     * @return 影响行数
     */
    int insert(Major major);

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 影响行数
     */
    int update(Major major);

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 影响行数
     */
    int deleteById(Integer mid);

    Integer queryGradeCount();

    //条件查询major
    List<SchoolManageVO> tjSelMajor(SchoolManageVO schoolManageVO);

    List<SchoolManageVO> jlSelMajor(SchoolManageVO schoolManageVO);

    //重复校验专业
    int checkOneMajor(Major major);

    //增加年级
    int addOneMajor(Major major);
}