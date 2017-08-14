package com.rdx.newsSOA.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by youxiaoshuang on 2016/11/22.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Data
public class GeetestResponse implements Serializable {
    private static final long serialVersionUID = 9117231987088160044L;
    private String status;
    private String version;
}
