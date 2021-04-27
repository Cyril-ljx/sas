package com.lingnan.sas.client.service;

import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.core.entity.Clock;
import com.lingnan.sas.core.entity.ClockVO;
import com.lingnan.sas.core.entity.Diretion;
import org.apache.ibatis.annotations.Param;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 19399.
 * @date 2021/3/26.
 * @time 13:19
 */
public interface ClockService {

    ClockVO searchMessage(String username);

    int insert(Clock clock,int id);

    /**
     * 查找未打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotClock(Integer cid,Integer page,Integer length);

    /**
     * 查找已打卡的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchClock(Integer cid ,Integer page,Integer length);

    List<HashMap> searchMessageById(String username,Long page,Integer length);

    HashMap searchClockById(Integer id);

    R insertDiretion(Diretion diretion);

    /**
     * 按班级查找教师管理的年级已打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchClockNum(String username,Integer page,Integer length);

    /**
     * 按班级查找教师管理的年级未打卡的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotClockNum(String username,Integer page,Integer length);

    /**
     * 查找未打卡假期去向的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotDirection(Integer cid,Integer page,Integer length);

    /**
     * 查找已打卡假期去向的学生信息
     *
     * @return 信息
     */
    ArrayList<HashMap> searchDirection(Integer cid ,Integer page,Integer length);

    /**
     * 按班级查找教师管理的年级已打卡假期去向的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchDirectionNum(String username,Integer page,Integer length);

    /**
     * 按班级查找教师管理的年级未打卡假期去向的学生人数
     *
     * @return 信息
     */
    ArrayList<HashMap> searchNotDirectionNum(String username,Integer page,Integer length);


}
