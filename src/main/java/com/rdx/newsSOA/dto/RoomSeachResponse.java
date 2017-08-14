package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/12.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class RoomSeachResponse implements Serializable{

    private static final long serialVersionUID = 2609784601407800966L;
    private Integer id;
    private String name;
    private String type;
    private Integer buildId;
    private Integer danYuanId;
    private Integer roomId ;
    private String roomName;
}
