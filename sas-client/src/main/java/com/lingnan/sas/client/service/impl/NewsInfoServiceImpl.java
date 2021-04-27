package com.lingnan.sas.client.service.impl;


import com.lingnan.sas.client.mapper.MessageDao;
import com.lingnan.sas.client.mapper.MessageRefDao;
import com.lingnan.sas.client.service.MessageService;
import com.lingnan.sas.client.service.NewsInfoService;
import com.lingnan.sas.core.entity.MessageEntity;
import com.lingnan.sas.core.entity.MessageRefEntity;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.mapper.NewsinfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("newsInfoService")
public class NewsInfoServiceImpl implements NewsInfoService {

    @Autowired
    private NewsinfoDao newsinfoDao;


    @Override
    public List<Newsinfo> queryNewsByPage(int offset, int limit) {
        return newsinfoDao.queryNewsByPage(offset,limit);
    }

    @Override
    public Newsinfo queryNewsById(int id) {
        return newsinfoDao.queryNewsById(id);
    }
}
