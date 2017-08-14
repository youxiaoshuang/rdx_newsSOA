package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.dto.TouTiaoNewModel;
import com.rdx.newsSOA.dto.NewsStatic;
import com.rdx.newsSOA.entity.YFile;
import com.rdx.newsSOA.util.JsonTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

public class TouTiaoHotPageProcessor implements PageProcessor {
    @Autowired
    private TouTiaoHotDetailProcessor touTiaoHotDetailProcessor;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

//    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 );

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .addHeader( "Host", "www.toutiao.com" )
            .addHeader( "User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:54.0) Gecko/20100101 Firefox/54.0" )
            .addHeader( "Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8" )
            .addHeader( "Accept-Language", "en-US,en;q=0.5" )
            .addHeader( "Accept-Encoding", "gzip, deflate" )
            .addHeader( "Cookie", "uuid=\"w:25a45b21390b4965af72ef9fb61b3a20\"; csrftoken=9b1ed4fe7578957f4449f645f8b377bf; CNZZDATA1259612802=456533365-1483603972-null%7C1498478369; _ga=GA1.2.1242994831.1483608561; tt_webid=6435921460971636226; _ba=BA0.2-20170626-51d9e-9ihO5SLt2VW2zEl0cy9e; UM_distinctid=15ce45ab4373af-03b6698e8981da-49556d-25800-15ce45ab438c7" )
            .addHeader( "Connection", "keep-alive" )
            .addHeader( "Upgrade-Insecure-Requests", "1" )
            .addHeader( "Cache-Control", "max-age=0" )
            .addCookie( "uuid", "\"w:25a45b21390b4965af72ef9fb61b3a20\"" )
            .addCookie( "csrftoken", "9b1ed4fe7578957f4449f645f8b377bf" )
            .addCookie( "CNZZDATA1259612802", "456533365-1483603972-null|1498478369" )
            .addCookie( "_ga", "GA1.2.1242994831.1483608561" )
            .addCookie( "tt_webid", "6435921460971636226" )
            .addCookie( "_ba", "BA0.2-20170626-51d9e-9ihO5SLt2VW2zEl0cy9e" )
            .addCookie( "UM_distinctid", "15ce45ab4373af-03b6698e8981da-49556d-25800-15ce45ab438c7" )
            .setCharset( "UTF-8" );

    @Override
    public void process(Page page) {
//        page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
//        page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//        if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//        System.out.printf( page.getRawText() );
        logger.info( "爬取到数据" );
        List<TouTiaoNewModel> newsList = getNewsList( page );
        logger.info( "分析数据" );
        for (TouTiaoNewModel touTiaoNewModel : newsList) {
            //保存新闻
            logger.info( "保存一条新闻   标题:{}", JsonTool.writeValueAsString( touTiaoNewModel.getTitle() ) );
            touTiaoHotDetailProcessor.Run( touTiaoNewModel, touTiaoHotDetailProcessor );
        }

    }

    public List<TouTiaoNewModel> getNewsList(Page page) {
        String json = page.getRawText();
        JSONObject jsonBean = JSONObject.fromObject( json );
        Object data = jsonBean.get( "data" );
        Object next = jsonBean.get( "next" );
        JSONArray jsonArray = JSONArray.fromObject( data );
        JSONArray nextArray = JSONArray.fromObject( next );
        JSONObject nextObject = (JSONObject) nextArray.get( 0 );
        NewsStatic.YC_max_behot_time = String.valueOf( nextObject.get( "max_behot_time" ) );
        List<TouTiaoNewModel> touTiaoNewModels = new ArrayList<TouTiaoNewModel>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get( i );
            String title = (String) jsonObject.get( "title" );
            String source = (String) jsonObject.get( "source" );
            String media_name = (String) jsonObject.get( "media_name" );
            String dateTime = (String) jsonObject.get( "dateTime" );
            String desc = (String) jsonObject.get( "abstract" );
            JSONArray labelArray = (JSONArray) jsonObject.get( "label" );
            String label = labelArray.getString( 0 );
            if (label != null && label.equals( "广告" )) continue;
            Boolean has_video = (Boolean) jsonObject.get( "has_video" );
            String item_source_url = (String) jsonObject.get( "item_source_url" );
            String source_url = (String) jsonObject.get( "source_url" );
            StringBuffer url = new StringBuffer( "https://www.toutiao.com/" );
            if (item_source_url != null) {
                url.append( item_source_url );
            } else {
                url.append( source_url );
            }
            JSONArray image_list = (JSONArray) jsonObject.get( "image_list" );
            TouTiaoNewModel touTiaoNewModel = new TouTiaoNewModel();
            touTiaoNewModel.setDateTime( dateTime );
            touTiaoNewModel.setMedia_name( media_name );
            touTiaoNewModel.setSource( source );
            touTiaoNewModel.setTitle( title );
            touTiaoNewModel.setDesc( desc );
            touTiaoNewModel.setUrl( url.toString() );
            if (image_list != null && image_list.size() > 0) {
                List<YFile> yFiles = new ArrayList<YFile>();
                for (int j = 0; j < image_list.size(); j++) {
                    YFile yFile = new YFile();
                    JSONObject fileObject = (JSONObject) image_list.get( j );
                    yFile.setUrl( (String) fileObject.get( "url" ) );
                    yFile.setIsLocalFile( 0 );
                    boolean add = yFiles.add( yFile );
                }
                touTiaoNewModel.setYFiles( yFiles );
            }
            touTiaoNewModels.add( touTiaoNewModel );

        }
        System.out.println( JsonTool.writeValueAsString( touTiaoNewModels ) );
        return touTiaoNewModels;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(TouTiaoHotPageProcessor githubRepoPageProcessor, String url) {
        Spider.create( githubRepoPageProcessor ).addUrl( url ).thread( 1 ).run();
    }

    public static void main(String[] args) {
        Long dateTime = new Date().getTime();
        Spider.create( new TouTiaoHotPageProcessor() ).addUrl( "http://www.toutiao.com/api/pc/feed/?category=news_hot&utm_source=toutiao&widen=1&max_behot_time=0&max_behot_time_tmp=0&as=0&cp=598C835B9AB44E1" ).thread( 1 ).run();
    }
}