package com.lingnan.sas.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbHolidaysDao {
    public Integer searchTodayIsHolidays();
    //查看是不是特殊节假日
    public ArrayList<String> searchHolidaysInRange(HashMap param);
}