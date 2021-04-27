package com.lingnan.sas.admin.service.impl;

;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lingnan.sas.admin.service.MajorService;
import com.lingnan.sas.core.entity.Major;
import com.lingnan.sas.core.entity.SchoolManageVO;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.MajorDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 专业(Major)表服务实现类
 *
 * @author makejava
 * @since 2021-03-15 16:48:11
 */
@Service("majorService")
public class MajorServiceImpl implements MajorService {
    @Resource
    private MajorDao majorDao;

    /**
     * 通过ID查询单条数据
     *
     * @param mid 主键
     * @return 实例对象
     */
    @Override
    public Major queryById(Integer mid) {
        return this.majorDao.queryById(mid);
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
        List<SchoolManageVO> majors= majorDao.queryAllByLimit(schoolManageVO);
        PageInfo pageInfo = new PageInfo(majors);
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
     * @param major 实例对象
     * @return 实例对象
     */
    @Override
    public Major insert(Major major) {
        this.majorDao.insert(major);
        return major;
    }

    /**
     * 修改数据
     *
     * @param major 实例对象
     * @return 实例对象
     */
    @Override
    public Major update(Major major) {
        this.majorDao.update(major);
        return this.queryById(major.getMid());
    }

    /**
     * 通过主键删除数据
     *
     * @param mid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer mid) {
        return this.majorDao.deleteById(mid) > 0;
    }

    @Override
    public Object queryAll() {
        return majorDao.queryAll(null);
    }

    @Override
    public List<SchoolManageVO> tjSelMajor(SchoolManageVO schoolManageVO) {
        return majorDao.tjSelMajor(schoolManageVO);
    }

    @Override
    public List<SchoolManageVO> jlSelMajor(SchoolManageVO schoolManageVO) {
        return majorDao.jlSelMajor(schoolManageVO);
    }


    @Override
    public Response addOneMajor(Major major) {
        int check = majorDao.checkOneMajor(major);
        if (check > 0) {
            return Response.error("有重复的专业不可添加");
        } else {
            int r = majorDao.addOneMajor(major);
            if (r == 1) {
                return Response.success("添加成功");
            } else {
                return Response.error("添加失败");
            }
        }
    }
}