package com.lingnan.sas.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Deliver)实体类
 *
 * @author makejava
 * @since 2021-04-02 01:40:47
 */
@Data
public class Deliver implements Serializable {
    private static final long serialVersionUID = 377608024944282467L;
    
    private Integer id;
    
    private String company;
    
    private String state;
    
    private String job;
    
    private String passData;
    
    private Integer suid;
    
    private String remark;

    private String time;


}