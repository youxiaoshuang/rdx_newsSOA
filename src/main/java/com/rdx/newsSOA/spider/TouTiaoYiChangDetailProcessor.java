package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.dao.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.face.NewsService;
import com.rdx.newsSOA.util.JsonTool;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;

public class TouTiaoYiChangDetailProcessor implements PageProcessor {
    @Autowired
    NewsService newsService;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 );

    @Setter
    @Getter
    private TouTiaoNewModel touTiaoNewModel;

    @Override
    public void process(Page page) {
        getNewsDetail( page );
    }

    public TouTiaoNewModel getNewsDetail(Page page) {
        page.putField( "content", page.getHtml().xpath( "//div[@class='article-content']/" ).all() );
        ResultItems resultItems = page.getResultItems();
        ArrayList<String> contentList = resultItems.get( "content" );
        StringBuffer stringBuffer = new StringBuffer( "" );
        for (String s : contentList) {
            stringBuffer.append( s );
        }
        String contentHtml = stringBuffer.toString();
        if (StringUtils.isNotEmpty( contentHtml )) {//今日头条编辑
            touTiaoNewModel.setContent( contentHtml );
            touTiaoNewModel.setSourceTye( 2 );//今日头条编辑类型
        } else {//网站超链接
            touTiaoNewModel.setSourceTye( 3 );//网页
        }
        logger.info( "调用nesService 插入新闻数据 入参:{}", JsonTool.writeValueAsString( touTiaoNewModel ) );
        NDoucument nDoucument = newsService.addTTNews( touTiaoNewModel );
        return touTiaoNewModel;
    }


    public void Run(TouTiaoNewModel touTiaoNewModel, TouTiaoYiChangDetailProcessor touTiaoYiChangDetailProcessor) {
        touTiaoYiChangDetailProcessor.setTouTiaoNewModel( touTiaoNewModel );
        Spider.create( touTiaoYiChangDetailProcessor ).addUrl( touTiaoNewModel.getUrl() ).thread( 1 ).run();
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String url = "http://www.toutiao.com/a6358714776495603969/";
        Spider.create( new TouTiaoYiChangDetailProcessor() ).addUrl( url ).thread( 1 ).run();
    }
}