package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/11.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class BuildingSeachResponse implements Serializable{
    private static final long serialVersionUID = 7713423354552498141L;
    private Integer id;
    private String name;
    private String type;

    private Integer estateId;
    private Integer buildingId ;
    private String buildingName;
}
