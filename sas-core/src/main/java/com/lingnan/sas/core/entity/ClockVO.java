package com.lingnan.sas.core.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * (Clock)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
@Data
public class ClockVO implements Serializable {
    private static final long serialVersionUID = 856541867244377046L;

    @NotNull
    @Min(1)
    private Integer page;
    @NotNull
    @Range(min = 1,max = 40)
    private Integer length;
    
    private Integer id;
    
    private Integer sid;
    
    private String sname;
    
    private Object temperature;
    
    private String city;
    
    private String time;
    
    private String remark;

    private String flag;

}