package com.lingnan.sas.admin.service.impl;


import com.lingnan.sas.admin.service.StudentService;
import com.lingnan.sas.admin.util.ShiroUtil;
import com.lingnan.sas.core.entity.*;
import com.lingnan.sas.core.util.LayuiVO;
import com.lingnan.sas.core.vo.Response;
import com.lingnan.sas.mapper.StudentDao;
import com.lingnan.sas.mapper.SysUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 学生(Student)表服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param sid 主键
     * @return 实例对象
     */
    @Override
    public Student queryById(Integer sid) {
        return this.studentDao.queryById(sid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public LayuiVO queryAllByLimit(int offset, int limit,String sname,Integer sid, Integer classid) {
        List<Student> students = studentDao.queryAllByLimit(offset, limit,sname,sid,classid);
        LayuiVO layData = new LayuiVO();
        layData.setCode(0);
        layData.setMsg("");
        layData.setCount(studentDao.queryStudentCount(sname,sid,classid));//总数
        layData.setData(students);
        return layData;
    }

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Student insert(Student student) {
        this.studentDao.insert(student);
        return student;
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Override
    public Response update(Student student) {
        if (studentDao.update(student) > 0) {
            return Response.success(student);
        }
        return Response.error("修改失败");
    }

    /**
     * 通过主键删除数据
     *
     * @param sid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer sid) {
        return this.studentDao.deleteById(sid) > 0;
    }

    @Override
    public Integer queryStudentCount(String sname,Integer sid, Integer classid) {
        return studentDao.queryStudentCount(sname,sid,classid);
    }

    @Override
    public Object queryAll() {
        return studentDao.queryAll(null);
    }

    //  查询系部
    @Override
    public List<Department> selDepartment() {
        return studentDao.selDepartment();
    }


    //  根据系部查询专业
    @Override
    public List<Major> selMajor(Integer did) {
        return studentDao.selMajor(did);
    }

    //  根据专业查询年级
    @Override
    public List<Grade> selGrade(Integer mid) {
        return studentDao.selGrade(mid);
    }

    //  根据年级查询班级
    @Override
    public List<Classinfo> selClassinfo(Integer gid) {
        return studentDao.selClassinfo(gid);
    }

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 实例对象
     */
    @Transactional
    @Override
    public Response add(Student student) {
        if (studentDao.finfById(student.getStuid()) > 0) {
            return Response.error("已存在学生学号不允许新增");
        }
        student.setSid(Integer.valueOf(student.getStuid()));
        student.setLeavetime(null);
        student.setMim("111111");
        student.setStustate(0);
        student.setIsDel(false);
        student.setRoleId(2);
        if (studentDao.insert(student) > 0) {
            //  班级人数+1
            Integer selClassinfo = studentDao.selecteClassinfo(student.getClassid());
            //  年级人数+1
            Integer selGrade = studentDao.selecteGrade(student.getGid());
            //  专业人数+1
            Integer selMajor = studentDao.selecteMajor(student.getMid());
            //  系部人数+1
            Integer selDepartment = studentDao.selecteDepartment(student.getDid());
            //添加系统用户
            SysUser sysUser = new SysUser();
            sysUser.setLoginName(student.getStuid());
            sysUser.setTel(student.getSphone());
            sysUser.setRoleId(1);
            sysUser.setSalt(ShiroUtil.createSalt());
            sysUser.setPassword(ShiroUtil.salt("123456", sysUser.getSalt()));
            sysUser.setLocked(false);
            System.out.println(sysUser);
            int result = sysUserDao.insert(sysUser);
            if (selClassinfo == 0 || selGrade == 0 || selMajor == 0 || selDepartment == 0) {
                throw new RuntimeException("添加学生失败，关联的上级人数不增加！");
            }
            return Response.success(student);

        }
        return Response.error("修改失败");
    }
}