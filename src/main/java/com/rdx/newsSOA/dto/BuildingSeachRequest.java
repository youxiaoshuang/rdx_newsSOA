package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/11.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class BuildingSeachRequest implements Serializable{
    private static final long serialVersionUID = -6764234385426634046L;
    private String city;
    private Integer id;
    private String type;
    private Integer estateId;
}
