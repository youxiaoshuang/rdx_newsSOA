package com.rdx.newsSOA.task;

import com.rdx.newsSOA.spider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Lazy(false)
public class SpiderTask {
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    @Autowired
    private TouTiaoYiChangPageProcessor touTiaoYiChangPageProcessor;
    @Autowired
    private TouTiaoHotPageProcessor touTiaoHotPageProcessor;
    @Autowired
    private NeiHanHotPageProcessor neiHanHotPageProcessor;
    @Autowired
    NeiHanImagePageProcessor neiHanImagePageProcessor;
    @Autowired
    DouTuDetailProcessor douTuDetailProcessor;

    @Scheduled(cron = "0/60 * * * * ?")
    public void run() {
        new Thread( new Runnable() {
            @Override
            public void run() {
                spiderYCRun();
            }
        } ).run();
        new Thread( new Runnable() {
            @Override
            public void run() {
                spiderHotRun();
            }
        } ).run();
        new Thread( new Runnable() {
            @Override
            public void run() {
                spiderNeiHanDuanZiRun();
            }
        } ).run();
        new Thread( new Runnable() {
            @Override
            public void run() {
                spiderNeiImageZiRun();
            }
        } ).run();
        new Thread( new Runnable() {
            @Override
            public void run() {
                spiderDouTuRun();
            }
        } ).run();


    }

    /**
     * 刷宜昌相关新闻
     * 每一小时执行一次
     */
    public void spiderYCRun() {
        Long dateTime = new Date().getTime();
        String url = "http://www.toutiao.com/search_content/?offset=60&format=json&keyword=%E5%AE%9C%E6%98%8C&autoload=true&count=10&_=" + dateTime;
        logger.info( "执行爬去今日头条宜昌新闻任务 URL:" + url );
        touTiaoYiChangPageProcessor.run( touTiaoYiChangPageProcessor, url );
    }

    /**
     * 刷头条热文新闻
     * 每60秒执行一次
     */
    public void spiderHotRun() {
//        Long dateTime = new Date().getTime();
//        String hotStaticTime = NewsStatic.YC_max_behot_time;
        String url = "http://www.toutiao.com/api/pc/feed/?category=__all__&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=A125C8B4A7AFA75&cp=583E9D110BFD1E1";
        logger.info( "执行爬去今日头条热门任务 URL:" + url );
        touTiaoHotPageProcessor.run( touTiaoHotPageProcessor, url );
    }


    /**
     * 刷内涵段子
     * 每60秒执行一次
     */
    public void spiderNeiHanDuanZiRun() {
        Long dateTime = new Date().getTime();
//        String hotStaticTime = NewsStatic.YC_max_behot_time;
        String url = "http://m.neihanshequ.com/?is_json=1&app_name=neihanshequ_web&min_time=" + dateTime + "&csrfmiddlewaretoken=783a7367ddad6fa93161b76e649a59c0";
        logger.info( "执行爬去内涵段子任务 URL:" + url );
        neiHanHotPageProcessor.run( neiHanHotPageProcessor, url );
    }

    /**
     * 刷内涵段子图片
     * 每60秒执行一次
     */
    public void spiderNeiImageZiRun() {
        Long dateTime = new Date().getTime();
//        String hotStaticTime = NewsStatic.YC_max_behot_time;
        String url = "http://m.neihanshequ.com/pic/?is_json=1&app_name=neihanshequ_web&min_time=" + dateTime + "&csrfmiddlewaretoken=783a7367ddad6fa93161b76e649a59c0";
        logger.info( "执行爬去内涵图片任务 URL:" + url );
        neiHanImagePageProcessor.run( neiHanImagePageProcessor, url );
    }

    public void spiderDouTuRun() {
        Long dateTime = new Date().getTime();
//        String hotStaticTime = NewsStatic.YC_max_behot_time;
        String url = "http://md.itlun.cn";
        logger.info( "执行斗图爬虫任务 URL:" + url );
        douTuDetailProcessor.run( douTuDetailProcessor, url );
    }


}
