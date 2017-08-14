package com.rdx.newsSOA.common.constants.SessionConstants.Global;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by youxiaoshuang on 2017/6/28.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public enum RedisKeyEnum {
    SPIDERKEYWORD( "spiderKeyWord", "爬取关键字" ),
    DOC_BROWSER( "doc_browser", "文章浏览量" ),
    WEB_BROWSER( "web_browser", "网站浏览量" );
    @Setter
    @Getter
    private String keyName;
    @Setter
    @Getter
    private String desc;


    RedisKeyEnum(String keyName, String desc) {
        this.keyName = keyName;
        this.desc = desc;
    }


    public static String getDocKeyName(int docId) {
        return WEB_BROWSER.getKeyName() + ":"+docId;
    }


}
