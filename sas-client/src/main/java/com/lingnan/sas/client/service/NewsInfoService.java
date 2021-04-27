package com.lingnan.sas.client.service;



import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;
import com.lingnan.sas.core.entity.Newsinfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface NewsInfoService {
    List<Newsinfo> queryNewsByPage(int offset, int limit);;

    Newsinfo queryNewsById(int id);
}
