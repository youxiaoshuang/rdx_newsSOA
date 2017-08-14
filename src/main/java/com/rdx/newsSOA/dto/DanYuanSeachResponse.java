package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2017/4/12.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class DanYuanSeachResponse implements Serializable{

    private static final long serialVersionUID = 7306627689259340907L;
    private Integer id;
    private String name;
    private String type;
    private Integer estateId;
    private Integer buildId;
    private Integer danYuanId ;
    private String danYuanName;
}
