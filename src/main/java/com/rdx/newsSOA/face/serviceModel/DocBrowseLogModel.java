package com.rdx.newsSOA.face.serviceModel;

import lombok.Data;

import java.util.Date;

/**
 * Created by youxiaoshuang on 2017/6/28.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class DocBrowseLogModel {
    private int id;
    private int docId;
    private int userId;
    private Date createTime;
    private Date updateTime;
}
