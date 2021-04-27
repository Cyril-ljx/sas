package com.lingnan.sas.core.entity;

import java.io.Serializable;

/**
 * (LeagueBranch)实体类
 *
 * @author makejava
 * @since 2021-03-11 15:31:15
 */
public class LeagueBranch implements Serializable {
    private static final long serialVersionUID = -35010077355347787L;
    
    private Integer id;
    
    private String leagueName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

}