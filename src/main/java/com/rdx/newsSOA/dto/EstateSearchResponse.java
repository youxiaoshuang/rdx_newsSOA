package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/10.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class EstateSearchResponse implements Serializable{
    private static final long serialVersionUID = -7516492506658409572L;
    private Integer id;
    private String estateName;
    private Integer fggEstateId;//房估估estateid
    private String cityName;
    private Integer estateId;//悟空找房estateId
    private String similarAddress;
    private String residentialareaName_en;
    private String extraParam_en;
    private String address;
    private String extraParam_cn;
    private String alignName_en;
    private String score;
    private String alignName;
    private String districtFullName;
    private Integer residentialareaId;
    private String residentialareaName;
    private String districtFullName_en;
    private String hightLightField;
    private String address_en;
}
