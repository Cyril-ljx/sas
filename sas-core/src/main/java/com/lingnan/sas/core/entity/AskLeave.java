package com.lingnan.sas.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (AskLeave)实体类
 *
 * @author makejava
 * @since 2021-04-07 23:16:07
 */
@Data
public class AskLeave implements Serializable {
    private static final long serialVersionUID = -20330465287855270L;
    
    private Integer id;
    
    private Integer sid;
    
    private Integer state;
    
    private String remark;
    
    private String reason;
    
    private String startTime;
    
    private String endTime;


}