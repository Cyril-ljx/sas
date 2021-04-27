package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.StuCourseService;
import com.lingnan.sas.core.entity.StuCourse;
import com.lingnan.sas.mapper.StuCourseDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (StuCourse)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:06:59
 */
@Service("stuCourseService")
public class StuCourseServiceImpl implements StuCourseService {
    @Resource
    private StuCourseDao stuCourseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param scid 主键
     * @return 实例对象
     */
    @Override
    public StuCourse queryById(Integer scid) {
        return this.stuCourseDao.queryById(scid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<StuCourse> queryAllByLimit(int offset, int limit) {
        return this.stuCourseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param stuCourse 实例对象
     * @return 实例对象
     */
    @Override
    public StuCourse insert(StuCourse stuCourse) {
        this.stuCourseDao.insert(stuCourse);
        return stuCourse;
    }

    /**
     * 修改数据
     *
     * @param stuCourse 实例对象
     * @return 实例对象
     */
    @Override
    public StuCourse update(StuCourse stuCourse) {
        this.stuCourseDao.update(stuCourse);
        return this.queryById(stuCourse.getScid());
    }

    /**
     * 通过主键删除数据
     *
     * @param scid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer scid) {
        return this.stuCourseDao.deleteById(scid) > 0;
    }
}