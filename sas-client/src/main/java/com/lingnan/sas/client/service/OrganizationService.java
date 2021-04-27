package com.lingnan.sas.client.service;

import com.lingnan.sas.core.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/3/26.
 * @time 13:19
 */
public interface OrganizationService {

    List<Student> queryStuByPage(int cid,int offset,  int limit);

    List<Classinfo> queryClassByPage(int gid,int offset,  int limit);

    List<Grade> queryGradeByPage(int mid,int offset,  int limit);

    List<Major> queryMajByPage(int did,int offset,  int limit);

    List<Department> queryDepByPage(int offset,  int limit);

}
