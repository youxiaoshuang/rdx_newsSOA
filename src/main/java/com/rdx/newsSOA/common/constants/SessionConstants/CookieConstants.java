package com.rdx.newsSOA.common.constants.SessionConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * cookie 静态资源类
 * Created by youxiaoshuang on 2017/4/10.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class CookieConstants {
    private static Map<String, String> cookieMap = new HashMap();

    public static void setCookieMap(Map cookieMap) {
        CookieConstants.cookieMap = cookieMap;
    }
    public static Map<String,String> getCookieMap(){
        return CookieConstants.cookieMap;
    }

    public static String jrbqiantai = "19F49CDFBD93171F80E3C56D21724DA0";

}
