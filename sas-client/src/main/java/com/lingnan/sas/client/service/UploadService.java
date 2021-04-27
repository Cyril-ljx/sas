package com.lingnan.sas.client.service;



import com.lingnan.sas.core.entity.Newsinfo;
import com.lingnan.sas.core.entity.UploadLog;
import com.qcloud.cos.transfer.Upload;

import java.util.List;

public interface UploadService {
    int insert(UploadLog uploadLog);
}
