package com.lingnan.sas.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 公告(Newsinfo)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:16
 */
@Data
public class Newsinfo implements Serializable {
    private static final long serialVersionUID = -66551299207709302L;
    
    private Integer nid;
    /**
    * 公告标题
    */
    private String ntitle;
    /**
    * 公告内容
    */
    private Object ncontent;
    /**
    * 公告发布人
    */
    private String nauthor;
    /**
    * 发布时间
    */
    private String ntime;
    /**
    * 发布图片
    */
    private String nimg;

    /*
    * 发布人id
    * */
    private String suid;

}