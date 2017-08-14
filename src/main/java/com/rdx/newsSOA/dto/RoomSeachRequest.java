package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/12.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class RoomSeachRequest implements Serializable{
    private static final long serialVersionUID = -856673056329546165L;
    private String city;
    private Integer id;
    private String type;
    private Integer danYuanId;
}
