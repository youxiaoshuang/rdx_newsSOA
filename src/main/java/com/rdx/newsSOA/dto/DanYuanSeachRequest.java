package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/12.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class DanYuanSeachRequest implements Serializable{
    private static final long serialVersionUID = -7493943208425220011L;
    private String city;
    private Integer id;
    private String type;
    private Integer buildId;
}
