package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.dto.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.face.NewsService;
import com.rdx.newsSOA.util.JsonTool;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TouTiaoHotDetailProcessor implements PageProcessor {
    @Autowired
    NewsService newsService;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 );

    @Override
    public void process(Page page) {
        getNewsDetail( page );
    }

    public TouTiaoNewModel getNewsDetail(Page page) {
        Map<String, Object> extras = page.getRequest().getExtras();
        TouTiaoNewModel touTiaoNewModel = (TouTiaoNewModel) extras.get( "touTiaoNewModel" );
        page.putField( "content", page.getHtml().xpath( "//*[@class='article__content']" ).all() );
        ResultItems resultItems = page.getResultItems();
        ArrayList<String> contentList = resultItems.get( "content" );
        StringBuffer stringBuffer = new StringBuffer( "" );
        for (String s : contentList) {
            stringBuffer.append( s );
        }
        String contentHtml = stringBuffer.toString();
//        //图集
//        if(StringUtils.isBlank( contentHtml )){
//            page.putField( "content", page.getHtml().xpath( "*/body/div/div[2]/div[1]/div/div/div[1]" ).all() );
//            resultItems = page.getResultItems();
//            contentList = resultItems.get( "content" );
//            for (String s : contentList) {
//                stringBuffer.append( s );
//            }
//            contentHtml = stringBuffer.toString();
//        }
        if (StringUtils.isNotEmpty( contentHtml )) {//今日头条编辑
            touTiaoNewModel.setContent( contentHtml );
            touTiaoNewModel.setSourceTye( 2 );//今日头条编辑类型
        } else {//网站超链接
            touTiaoNewModel.setSourceTye( 3 );//网页
        }
        logger.info( "调用nesService 插入新闻数据 标题:{}", JsonTool.writeValueAsString( touTiaoNewModel.getTitle() ) );
        NDoucument nDoucument = newsService.addTTNews( touTiaoNewModel );
        return touTiaoNewModel;
    }


    public void Run(TouTiaoNewModel touTiaoNewModel, TouTiaoHotDetailProcessor githubRepoDetailProcessor) {
        Map<String,Object> extraMap = new HashMap<>(  );
        extraMap.put( "touTiaoNewModel",touTiaoNewModel );
        Request request = new Request();
        request.setExtras( extraMap );
        request.setUrl( touTiaoNewModel.getUrl() );
        Spider.create( githubRepoDetailProcessor ).addRequest( request ).thread( 1 ).run();
    }


    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        setUrl( "http://www.toutiao.com/a6357221060198465793/" );
    }
}