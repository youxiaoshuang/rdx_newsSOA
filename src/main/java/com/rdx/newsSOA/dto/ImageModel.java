package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/1/11.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class ImageModel implements Serializable{
    private static final long serialVersionUID = -8334267537299364033L;
    private Integer id;
    private String url;
    private String key;
    private Integer isLocalImage;
}
