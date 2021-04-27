package com.lingnan.sas.client.service.impl;


import com.lingnan.sas.client.service.NewsInfoService;
import com.lingnan.sas.client.service.UploadService;
import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.entity.UploadLog;
import com.lingnan.sas.mapper.NewsinfoDao;
import com.lingnan.sas.mapper.UploadLogDao;
import com.qcloud.cos.transfer.Upload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadLogDao uploadLogDao;


    @Override
    public int insert(UploadLog uploadLog) {
        return uploadLogDao.insert(uploadLog);
    }
}
