package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.TeacherService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.Student;
import com.lingnan.sas.core.entity.SysUser;
import com.lingnan.sas.core.entity.Teacher;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.SysUserDao;
import com.lingnan.sas.mapper.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师表(Teacher)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:03
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Resource
    private TeacherDao teacherDao;

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param tid 主键
     * @return 实例对象
     */
    @Override
    public Teacher queryById(Integer tid) {
        return this.teacherDao.queryById(tid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(int offset, int limit, String tname, Integer tid, Integer gid) {
        List<Teacher> teachers =teacherDao.queryAllByLimit(offset, limit,tname,tid,gid);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(teacherDao.queryTeacherCount(tname,tid,gid));//总数
        layData.setData(teachers);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    @Override
    public Teacher insert(Teacher teacher) {
        this.teacherDao.insert(teacher);
        System.out.println(teacher);

        return teacher;
    }

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    @Override
    public Response update(Teacher teacher) {
        if(teacherDao.update(teacher)>0){
            return Response.success(teacher);
        }
        return Response.error("更新失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param tid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer tid) {
        return this.teacherDao.deleteById(tid) > 0;
    }

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 实例对象
     */
    @Override
    public Response add(Teacher teacher) {

        teacher.setLeavetime(null);
        teacher.setTchstate(0);
        teacher.setIsDel(false);
        teacher.setRoleId(1);
        if(teacherDao.insert(teacher)>0) {
            //添加系统用户
            SysUser sysUser = new SysUser();
            sysUser.setLoginName(String.valueOf(teacher.getTid()));
            sysUser.setTel(teacher.getTphone());
            sysUser.setRoleId(2);
            sysUser.setSalt(ShiroUtil.createSalt());
            sysUser.setPassword(ShiroUtil.salt("123456", sysUser.getSalt()));
            sysUser.setLocked(false);
            int result = sysUserDao.insert(sysUser);
            return Response.success(teacher);
        }
        return Response.error("修改失败");
    }
}