package com.lingnan.sas.client.service;

import com.lingnan.sas.core.entity.Student;

import java.util.HashMap;

/**
 * @author 19399.
 * @date 2021/3/26.
 * @time 13:19
 */
public interface StudentService {

    public int bindUser(String code,String nickname,String photo);

    Student searchMessage(String username);

    int update(Student student);

    Student queryById(String username);
}
