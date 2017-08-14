package com.rdx.newsSOA.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2016/11/3.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 7224996115412580747L;
    private T data;
    private String msg;
    private Integer status;
}
