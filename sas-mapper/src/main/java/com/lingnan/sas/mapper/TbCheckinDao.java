package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.TbCheckin;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
public interface TbCheckinDao {
    public Integer haveCheckin(HashMap param);
    public void insert(TbCheckin checkin);
    public HashMap searchTodayCheckin(int userId);
    public long searchCheckinDays(int userId);
    public ArrayList<HashMap> searchWeekCheckin(HashMap param);
}