package com.rdx.newsSOA.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class NDoucument implements Serializable{
    private static final long serialVersionUID = -6195314793694932436L;
    private Integer id;

    private String content;

    private String title;

    private String desc;

    private Integer status;

    private Date updatetime;

    private String publishTime;

    private Date createtime;

    private String url;

    private Integer imageSize;

    private List<YFile> images;

    private String uuid;

    private String md5;

    private Integer sourceType;

    private String sourceUrl;
}