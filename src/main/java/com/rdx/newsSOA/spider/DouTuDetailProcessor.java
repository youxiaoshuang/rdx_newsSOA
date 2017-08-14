package com.rdx.newsSOA.spider;


import com.rdx.newsSOA.dto.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.face.NewsService;
import com.rdx.newsSOA.util.HtmlUtil;
import com.rdx.newsSOA.util.JsonTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DouTuDetailProcessor implements PageProcessor {
    @Autowired
    private NewsService newsService;
    List<String> hrefs;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .setUserAgent( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36" )
            .addHeader( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8" )
            .addHeader( "Accept-Encoding", "ggzip, deflate, sdch, br" )
            .addHeader( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" )
            .addHeader( "Cache-Control", "no-cache" )
            .addHeader( "Connection", "keep-alive" )
            .addCookie( "__cfduid", "d131e78c253424402feca1384841ad5e11484735480" )
            .addCookie( "Hm_lvt_2bbfd0fdfb89ea4eb7308750c4b9ce92", "1484736045" )
            .addCookie( "Hm_lpvt_2bbfd0fdfb89ea4eb7308750c4b9ce92", "1484736045" )
            .addCookie( "Hm_lvt_aac62bb7daa996fa4c067159e26ef406", "1484735491,1484735631,1484735683,1484735753" )
            .addCookie( "Hm_lpvt_aac62bb7daa996fa4c067159e26ef406", "1484736255" )
            .addCookie( "CNZZDATA5439572", "cnzz_eid%3D680716633-1484732447-null%26ntime%3D1484732447" )
            .addCookie( "_ga", "GA1.2.1713791787.1484735492" )
            .addHeader( "Host", "md.itlun.cn" )
            .addHeader( "Referer", "http://md.itlun.cn/a/jgz/1.html" )
            .addHeader( "Upgrade-Insecure-Requests", "1" );

    @Override
    public void process(Page page) {
        List<TouTiaoNewModel> newsList = getNewsList( page );
    }

    public List<TouTiaoNewModel> getNewsList(Page page) {
        List<TouTiaoNewModel> touTiaoNewModels = new ArrayList<TouTiaoNewModel>();
        page.putField( "content", page.getHtml().xpath( "//*/li/a" ).all() );
        hrefs = HtmlUtil.getHref( page.getRawText() );
        ArrayList<String> contentList = page.getResultItems().get( "content" );
        for (String s : contentList) {
            TouTiaoNewModel touTiaoNewModel = new TouTiaoNewModel();
            String title = HtmlUtil.getSpen( s );
            if (StringUtils.isNotEmpty( title )) {
                touTiaoNewModel.setTitle( title );
                touTiaoNewModel.setContent( title );
                touTiaoNewModel.setDesc( title );
                touTiaoNewModel.setHasVideo( false );
                Set<String> imgStrs = HtmlUtil.getImgStr( s );
                List<YFile> yFiles = new ArrayList<YFile>();
                for (String imgStr : imgStrs) {
                    String fileType = imgStr.substring( imgStr.lastIndexOf( "." ), imgStr.length() );
                    YFile yFile = new YFile();
                    yFile.setFiletype( fileType );
                    yFile.setIsLocalFile( 0 );
                    yFile.setUrl( imgStr );
                    yFiles.add( yFile );
                }
                if(yFiles.size()<1){
                    return null;
                }
                touTiaoNewModel.setYFiles( yFiles );
                touTiaoNewModel.setSourceTye( 6 );
                System.out.println( JsonTool.writeValueAsString( touTiaoNewModel ));
                NDoucument nDoucument = newsService.addTTNews( touTiaoNewModel );
            }
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (String href : hrefs) {
                    Spider.create( new DouTuDetailProcessor() ).addUrl( href ).thread( 1 ).run();
                }
            }
        };
        runnable.run();
        return touTiaoNewModels;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(DouTuDetailProcessor douTuDetailProcessor, String url) {
        Spider.create( douTuDetailProcessor ).addUrl( url ).thread( 1 ).run();
    }

    public static void main(String[] args) {
        Long dateTime = new Date().getTime();
        Spider.create( new DouTuDetailProcessor() ).addUrl( "http://md.itlun.cn" ).thread( 1 ).run();
    }
}