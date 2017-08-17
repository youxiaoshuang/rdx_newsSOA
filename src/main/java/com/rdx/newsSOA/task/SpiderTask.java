package com.rdx.newsSOA.task;

import com.rdx.newsSOA.spider.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
//    @Autowired
//    private RedisClientAdapterImpl redisClientAdapter;


    /**
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void run() {
//        threadPoolTaskExecutor.execute( new Runnable() {
//            @Override
//            public void run() {
//                spiderYCRun();
//            }
//        } );
//        threadPoolTaskExecutor.execute( new Runnable() {
//            @Override
//            public void run() {
//                spiderNeiHanDuanZiRun();
//            }
//        } );
        threadPoolTaskExecutor.execute( new Runnable() {
            @Override
            public void run() {
                spiderHotRun();
            }
        } );
//        threadPoolTaskExecutor.execute( new Runnable() {
//            @Override
//            public void run() {
//                spiderNeiImageZiRun();
//            }
//        } );
//        threadPoolTaskExecutor.execute( new Runnable() {
//            @Override
//            public void run() {
//                spiderDouTuRun();
//            }
//        } );
//        threadPoolTaskExecutor.execute( new Runnable() {
//            @Override
//            public void run() {
//                spiderKeyWord();
//            }
//        } );
    }


    /**
     * 爬取键字新闻
     */

    public void spiderKeyWord(){
        Long dateTime = new Date().getTime();
//        String spiderKeyWord = (String) redisClientAdapter.get( "spiderKeyWord" );
        String spiderKeyWord = "宜昌,三峡,事故,宜都,清江,趣闻,搞笑,情趣,夷陵,长阳,五峰,日大侠,眼子鸡,峡州,彝陵,沮漳河,黄柏河,柏临河,香溪河,猇亭,点军区,伍家岗区,西陵区,当阳,枝江,兴山,秭归,葛洲坝,晓溪塔,磨基山,天然塔,文佛山";
        logger.info( "取得关键字集：{}"+spiderKeyWord );
        if(spiderKeyWord!=null){
            String[] split = spiderKeyWord.split( "," );
            if(split!=null && split.length>0){
                for (final String s : split) {
                    threadPoolTaskExecutor.execute( new Runnable() {
                        @Override
                        public void run() {
                            Long dateTime = new Date().getTime();
                            String url = "http://www.toutiao.com/search_content/?offset=60&format=json&keyword="+s+"&autoload=true&count=10&_=" + dateTime;
                            logger.info( "执行爬取关键字"+s+"新闻任务 URL:" + url );
                            touTiaoYiChangPageProcessor.run( touTiaoYiChangPageProcessor, url );
                        }
                    } );
                }
            }
        }
        String url = "http://www.toutiao.com/search_content/?offset=60&format=json&keyword="+"视频"+"&autoload=true&count=10&_=" + dateTime;
        logger.info( "执行爬取关键字"+"宜昌"+"新闻任务 URL:" + url );
        touTiaoYiChangPageProcessor.run( touTiaoYiChangPageProcessor, url );
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
        Long dateTime = new Date().getTime();
//        String hotStaticTime = NewsStatic.YC_max_behot_time;
        String url = "http://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=0&cp=598C835B9AB44E1";
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
