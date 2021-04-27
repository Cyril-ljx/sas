package com.lingnan.sas.mapper;


import com.lingnan.sas.core.entity.TbFaceModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFaceModelDao {
    public String searchFaceModel(int userId);
    public void insert(TbFaceModel faceModel);
    public int deleteFaceModel(int userId);
}