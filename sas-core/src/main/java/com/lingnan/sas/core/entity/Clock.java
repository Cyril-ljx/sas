package com.lingnan.sas.core.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Clock)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
@Data
public class Clock implements Serializable {
    private static final long serialVersionUID = 856541867244377046L;
    
    private Integer id;
    
    private Integer sid;
    
    private String sname;
    
    private Object temperature;
    
    private String city;
    
    private String time;
    
    private String remark;

    private Integer flag;

    private String district;

    private String address;

    private Integer risk;

}