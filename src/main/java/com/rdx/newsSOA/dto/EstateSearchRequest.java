package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/10.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class EstateSearchRequest implements Serializable {
    private static final long serialVersionUID = 212760940606225294L;
    private String cityName;
    private String estateName;
    private String areaName;
    private String accurateType;
    private String matchType;
    private Integer estateId;
}
