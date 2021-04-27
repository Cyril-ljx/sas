package com.lingnan.sas.client.service;



import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.core.entity.AskLeave;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;

public interface AskLeaveService {
    ArrayList<HashMap> queryAllById(int id, int offset, int limit);

    AskLeave queryAskLeaveById(int id);

    ArrayList<HashMap> queryAllOverById(int id, int offset, int limit);

    int updateAskLeave(AskLeave dskLeave);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 新增数据
     *
     * @param dskLeave 实例对象
     * @return 影响行数
     */
    R insert(AskLeave dskLeave);

    /**
     * 根据教师id查找未请假的
     *
     * @param username 教师id
     * @return 影响行数
     */
    ArrayList<HashMap> searchAskLeave(String username, int offset, int limit);
    /**
     * 根据教师id查找已请假的
     *
     * @param username 教师id
     * @return 影响行数
     */
    ArrayList<HashMap> searchApprove(String username, int offset, int limit);


}
