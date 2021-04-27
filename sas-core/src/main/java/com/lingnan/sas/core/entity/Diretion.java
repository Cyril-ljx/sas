package com.lingnan.sas.core.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Diretion)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
@Data
public class Diretion implements Serializable {
    private static final long serialVersionUID = 449729421856021594L;
    
    private Integer id;
    
    private Integer sid;
    
    private String diretion;
    
    private String leftTime;
    
    private String backTime;

    private String sname;

    private String remark;
}