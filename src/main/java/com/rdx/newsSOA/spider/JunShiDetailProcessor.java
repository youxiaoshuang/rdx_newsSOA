package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.dto.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.face.NewsService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.*;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.PlainText;

import java.util.ArrayList;

@Log4j
public class JunShiDetailProcessor implements PageProcessor {
    @Autowired
    NewsService newsService;
    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 ).setTimeOut( 10000 ).setUserAgent( "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1" );

    @Override
    public void process(Page page) {
        Request request = page.getRequest();
        page.putField( "title",page.getHtml().xpath( "//*[@class=\"J-title_detail title_detail\"]/h1/span/text()" ) );
        page.putField( "content", page.getHtml().xpath( "//*[@class=\"J-contain_detail_cnt contain_detail_cnt\"]" ).all() );
        ResultItems resultItems = page.getResultItems();
        ArrayList<String> contentList = resultItems.get( "content" );
        StringBuffer stringBuffer = new StringBuffer( "" );
        for (String s : contentList) {
            stringBuffer.append( s );
        }
        String contentHtml = stringBuffer.toString();
        TouTiaoNewModel touTiaoNewModel = new TouTiaoNewModel();
        PlainText plainText = resultItems.get( "title" );
        String title = plainText.toString();
        touTiaoNewModel.setContent( contentHtml );
        touTiaoNewModel.setTitle( title );
        touTiaoNewModel.setSource( page.getUrl().toString() );
        touTiaoNewModel.setDesc( title );
        touTiaoNewModel.setHasVideo( false );
        touTiaoNewModel.setSourceTye( 4 );
        log.info( "调用nesService 插入新闻数据 标题:"+touTiaoNewModel.getTitle() );
        NDoucument nDoucument = newsService.addTTNews( touTiaoNewModel );
    }

    @Override
    public Site getSite() {
        return site;
    }


    public static void main(String[] args) {

        Spider.create(new DouTuDetailProcessor()).addUrl( "http://news.dwnews.com/global/news/2017-09-04/60010624.html" ).thread( 1 ).run();
    }

}