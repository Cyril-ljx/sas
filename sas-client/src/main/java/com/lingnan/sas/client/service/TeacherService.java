package com.lingnan.sas.client.service;

import com.lingnan.sas.core.entity.Teacher;

/**
 * @author 19399.
 * @date 2021/3/26.
 * @time 13:19
 */
public interface TeacherService {

    public int bindUser(String code, String nickname, String photo);

    Teacher searchMessage(String username);

    int update(Teacher teacher);

    Teacher queryById(String username);
}
