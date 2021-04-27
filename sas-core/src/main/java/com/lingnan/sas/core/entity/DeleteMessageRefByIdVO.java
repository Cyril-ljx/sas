package com.lingnan.sas.core.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class DeleteMessageRefByIdVO {
    @NotNull
    private String id;
}
