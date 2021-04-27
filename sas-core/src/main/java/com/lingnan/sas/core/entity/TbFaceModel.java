package com.lingnan.sas.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tb_face_model
 * @author 
 */
@Data
public class TbFaceModel implements Serializable {
    /**
     * 主键值
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户人脸模型
     */
    private String faceModel;

    private static final long serialVersionUID = 1L;
}