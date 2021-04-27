package com.lingnan.sas.admin.service.impl;


import com.github.pagehelper.PageHelper;
import com.lingnan.sas.admin.service.DepartmentService;
import com.lingnan.sas.core.entity.Department;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.DepartmentDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系(Department)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:06:58
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentDao departmentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param did 主键
     * @return 实例对象
     */
    @Override
    public Department queryById(Integer did) {
        return this.departmentDao.queryById(did);
    }

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO, int page, int limit) {
        PageHelper.startPage(page,limit);
        List<SchoolManageVO> departments = departmentDao.queryAllByLimit(schoolManageVO);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(departmentDao.queryDepartmentCount());//总数
        layData.setData(departments);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department insert(Department department) {
        this.departmentDao.insert(department);
        return department;
    }

    /**
     * 修改数据
     *
     * @param department 实例对象
     * @return 实例对象
     */
    @Override
    public Department update(Department department) {
        this.departmentDao.update(department);
        return this.queryById(department.getDid());
    }

    /**
     * 通过主键删除数据
     *
     * @param did 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer did) {
        return this.departmentDao.deleteById(did) > 0;
    }

    @Override
    public Object queryAll() {
        return departmentDao.queryAll(null);
    }
    //条件查询department
    @Override
    public List<SchoolManageVO> tjSelDpm(SchoolManageVO schoolManageVO) {
        return departmentDao.tjSelDpm(schoolManageVO);
    }

    //重复验证院系
    @Override
    public int checkOneDpm(Department department) {
        return departmentDao.checkOneDpm(department);
    }

    //添加院系
    @Override
    public Response addOneDpm(Department department) {
        int check = departmentDao.checkOneDpm(department);
        if (check > 0) {
            return Response.error("有重复的院系不可添加！");
        } else {
            int r = departmentDao.addOneDpm(department);
            if (r == 1) {
                return Response.success("添加成功");
            } else {
                return Response.error("添加失败");
            }
        }
    }
}