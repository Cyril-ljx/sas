package com.lingnan.sas.core.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (UploadLog)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
public class UploadLog implements Serializable {
    private static final long serialVersionUID = -23103366027840898L;
    
    private Integer id;
    
    private Integer userId;
    
    private String message;
    
    private Date createTime;
    
    private String fileUrl;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

}