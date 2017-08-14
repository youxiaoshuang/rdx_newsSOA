package com.rdx.newsSOA.util;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by youxiaoshuang on 2017/5/31.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class CacheUtil {
    private static Map<String, Object> cacheMap = new Hashtable<>();

    public static Map<String, Object> set(String key, Object o) {
        cacheMap.put( key, o );
        return cacheMap;
    }

    public static Object get(String key) {
        return cacheMap.get( key );
    }
}
