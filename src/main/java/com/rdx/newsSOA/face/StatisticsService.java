package com.rdx.newsSOA.face;

/**
 * 统计访问量
 * Created by youxiaoshuang on 2017/6/28.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public interface StatisticsService {

    /**
     * 新增网站访问记录
     * @return
     */
    long addWebBrowser();

    /**
     * 新增一条新闻访问记录
     * @param docId
     * @param userId
     * @return
     */
    long adddocrowser(Integer docId,Integer userId);
}
