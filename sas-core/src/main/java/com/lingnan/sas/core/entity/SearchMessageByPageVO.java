package com.lingnan.sas.core.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class SearchMessageByPageVO {
    @NotNull
    @Min(1)
    private Integer page;
    @NotNull
    @Range(min = 1,max = 40)
    private Integer length;
}
