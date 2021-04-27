package com.lingnan.sas.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.GradeService;
import com.lingnan.sas.core.entity.Grade;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.GradeDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 年级(Grade)表服务实现类
 *
 * @author makejava
 * @since 2021-03-15 16:48:12
 */
@Service("gradeService")
public class GradeServiceImpl implements GradeService {
    @Resource
    private GradeDao gradeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param gid 主键
     * @return 实例对象
     */
    @Override
    public Grade queryById(Integer gid) {
        return this.gradeDao.queryById(gid);
    }

    /**
     * 查询多条数据
     *
     * @param page 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(SchoolManageVO schoolManageVO,int page, int limit) {
        PageHelper.startPage(page, limit);
        List<SchoolManageVO> grades= gradeDao.queryAllByLimit(schoolManageVO);
        PageInfo pageInfo = new PageInfo(grades);
        LayuiVO layData =new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount((int) pageInfo.getTotal());//总数
        layData.setData(pageInfo.getList());
        return layData;
    }

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public Grade insert(Grade grade) {
        this.gradeDao.insert(grade);
        return grade;
    }

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    @Override
    public Grade update(Grade grade) {
        this.gradeDao.update(grade);
        return this.queryById(grade.getGid());
    }

    /**
     * 通过主键删除数据
     *
     * @param gid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer gid) {
        return this.gradeDao.deleteById(gid) > 0;
    }

    @Override
    public List<SchoolManageVO> queryAll(SchoolManageVO schoolManageVO) {
        return gradeDao.queryAll(schoolManageVO);
    }

    //条件查询grade
    @Override
    public List<SchoolManageVO> tjSelGrade(SchoolManageVO schoolManageVO) {
        return gradeDao.tjSelGrade(schoolManageVO);
    }

    //级联查询grade
    @Override
    public List<SchoolManageVO> jlSelGrade(SchoolManageVO schoolManageVO) {
        return gradeDao.jlSelGrade(schoolManageVO);
    }


    @Override
    public Response addOneGrade(Grade grade) {
        int check = gradeDao.checkOneGrade(grade);
        if (check > 0) {
            return Response.error("已有该年级不可重复添加");
        } else {
            int r = gradeDao.addOneGrade(grade);
            if (r == 1) {
                return Response.success("添加年级成功");
            } else {
                return Response.error("添加失败");
            }
        }
    }
}