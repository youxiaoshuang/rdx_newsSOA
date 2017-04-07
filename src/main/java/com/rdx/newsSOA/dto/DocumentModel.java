package com.rdx.newsSOA.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


/**
 * Created by youxiaoshuang on 2017/1/11.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class DocumentModel implements Serializable{

    private static final long serialVersionUID = -348527872321504834L;
    private Integer id;
    private String uuid;
    private String title;
    private String comment;
    private String desc;
    private String createTime;
    private Integer imageSize;
    private List<ImageModel> images;
    private String type;
}
