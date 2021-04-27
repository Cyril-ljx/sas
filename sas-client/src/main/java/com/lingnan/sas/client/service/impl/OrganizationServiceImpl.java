package com.lingnan.sas.client.service.impl;


import com.lingnan.sas.client.service.ClockService;
import com.lingnan.sas.client.service.OrganizationService;
import com.lingnan.sas.core.entity.*;
import com.lingnan.sas.core.util.DateUtil;
import com.lingnan.sas.mapper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 组织架构服务实现类
 *
 * @author makejava
 * @since 2021-03-09 19:07:00
 */
@Service("organizationService")
@Scope("prototype")
public class OrganizationServiceImpl implements OrganizationService {


    @Resource
    private ClockDao clockDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private ClassinfoDao classinfoDao;

    @Resource
    private GradeDao gradeDao;

    @Resource
    private MajorDao majorDao;

    @Resource
    private DepartmentDao departmentDao;


    @Override
    public List<Student> queryStuByPage(int cid, int offset, int limit) {
        return studentDao.queryStuByPage(cid,offset,limit);
    }

    @Override
    public List<Classinfo> queryClassByPage(int gid, int offset, int limit) {
        return classinfoDao.queryClassByPage(gid,offset,limit);
    }

    @Override
    public List<Grade> queryGradeByPage(int mid, int offset, int limit) {
        return gradeDao.queryGradeByPage(mid,offset,limit);
    }

    @Override
    public List<Major> queryMajByPage(int did, int offset, int limit) {
        return majorDao.queryMajByPage(did,offset,limit);
    }

    @Override
    public List<Department> queryDepByPage(int offset, int limit) {
        return departmentDao.queryDepByPage(offset,limit);
    }
}