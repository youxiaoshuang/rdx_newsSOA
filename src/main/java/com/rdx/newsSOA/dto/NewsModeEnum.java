package com.rdx.newsSOA.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by youxiaoshuang on 2017/1/11.
 * Projiect fightting
 * Author youxiaoshuang
 */
public enum NewsModeEnum {
    NEWS_0(0,"新闻无图"),
    NEWS_1(1,"新闻一张图"),
    NEWS_2(2,"新闻两张图"),
    NEWS_3(3,"新闻三张图"),
    NEWS_4(4,"新闻多张图");
    @Setter
    @Getter
    private Integer code;//模板code
    @Setter
    @Getter
    private String desc;//模板描述

    NewsModeEnum(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
