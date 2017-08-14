package com.rdx.newsSOA.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by youxiaoshuang on 2017/4/10.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public enum UrlEnum {
    ESTATE_LIST_URL( "http://www.fungugu.com/JinRongGuZhi/splitAddress", "获取小区列表url" ),
    BUILD_LIST_URL( "http://www.fungugu.com/JinRongGuZhi/getLiandong", "获取楼栋列表 室号列表级联url" ),
    XUNJIASHISHIJILU_NOLOGIN_URL( "http://www.fungugu.com/api/xunJiaShiShiJiLu_noLogin?"+getTime(), "获取xunJiaShiShiJiLu_noLogin" ),
    PARTICIPLESEARCH_NOLOGIN("http://www.fungugu.com/JinRongGuZhi/participleSearch_noLogin","未登录获取小区列表url");
    @Setter
    @Getter
    private String url;
    @Setter
    @Getter
    private String desc;

    UrlEnum(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    static long getTime() {
        return new Date().getTime();
    }
}
