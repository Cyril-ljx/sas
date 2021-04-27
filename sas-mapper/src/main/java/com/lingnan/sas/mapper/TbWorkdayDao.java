package com.lingnan.sas.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbWorkdayDao {
    public Integer searchTodayIsWorkday();
    /*
    * 查找是不是工作日
    * */
    public ArrayList<String> searchWorkdayInRange(HashMap param);
}