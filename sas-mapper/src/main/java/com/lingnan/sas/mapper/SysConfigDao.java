package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysConfigDao {
    List<SysConfig> selectAllParam();
}