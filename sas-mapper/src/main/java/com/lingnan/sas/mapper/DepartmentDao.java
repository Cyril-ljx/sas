package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.Department;
import com.lingnan.sas.core.entity.SchoolManageVO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 系(Department)表数据库访问层
 *
 * @author makejava
 * @since 2021-03-09 18:33:19
 */
public interface DepartmentDao {

    /**
     * 通过ID查询单条数据
     *
     * @param did 主键
     * @return 实例对象
     */
    Department queryById(Integer did);

    /**
     * 分页查询
     *
     * @param offset 开始位置
    * @param limit 每页搜索数
     * @return 实例对象
     */
    List<Department> queryDepByPage(@Param("offset") int offset, @Param("limit") int limit);

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
     * @param department 实例对象
     * @return 对象列表
     */
    List<Department> queryAll(Department department);

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int insert(Department department);

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 影响行数
     */
    int update(Department department);

    /**
     * 通过主键删除数据
     *
     * @param did 主键
     * @return 影响行数
     */
    int deleteById(Integer did);

    Integer queryDepartmentCount();
    //条件查询department
    List<SchoolManageVO> tjSelDpm(SchoolManageVO schoolManageVO);

    //重复验证院系
    int checkOneDpm(Department department);

    //添加院系
    int addOneDpm(Department department);
}