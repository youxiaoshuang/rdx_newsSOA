package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.dto.TouTiaoNewModel;
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

public class TouTiaoYiChangPageProcessor implements PageProcessor {
    @Autowired
    private TouTiaoYiChangDetailProcessor touTiaoYiChangDetailProcessor;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );
//
//    @Autowired
//    private RedisClientAdapterImpl redisClientAdapter;

    //    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 );
    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .setUserAgent( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.98 Safari/537.36" )
            .addHeader( "Accept", "text/javascript, text/html, application/xml, text/xml, */*" )
            .addHeader( "Accept-Encoding", "gzip, deflate, sdch, br" )
            .addHeader( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" )
            .addHeader( "Cache-Control", "no-cache" )
            .addHeader( "Connection", "keep-alive" )
            .addCookie( "no-cache", "w:10940df6ae9b45b58f0f055f6b775dc8" )
            .addCookie( "cp", "583E9D110BFD1E1" )
            .addCookie( "__utma", "24953151.1964721584.1472474880.1480398017.1480512393.3" )
            .addCookie( "__utmz", "24953151.1480391746.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none)" )
            .addCookie( "login_flag", "b94f12620f33109ce5dc9c46327396bd" )
            .addCookie( "sid_tt", "10c8d9ccc00b6ff2ebc78d3604de5f19" )
            .addCookie( "sessionid", "10c8d9ccc00b6ff2ebc78d3604de5f19" )
            .addCookie( "sid_guard", "10c8d9ccc00b6ff2ebc78d3604de5f19|1480845873|2592000|Tue\\054 03-Jan-2017 10:04:33 GMT" )
            .addCookie( "csrftoken", "d4b4ba5bf7989f636600a7ab4873273e" )
            .addCookie( "tt_webid", "27059611200" )
            .addCookie( "CNZZDATA1259612802", "587757308-1476007816-null%7C1481110821" )
            .addCookie( "_ga", "GA1.2.1964721584.1472474880" )
            .addCookie( "__tasessionId", "iuc20sx3s1481111917461" )
            .addHeader( "Host", "www.toutiao.com" )
            .addHeader( "Pragma", "no-cache" )
            .addHeader( "Referer", "https://www.toutiao.com/news_hot/" )
            .addHeader( "X-Requested-With", "XMLHttpRequest" )
            .addHeader( "Cookie", "uuid=\"w:f4fda41206df471a90c2988bb4b138b4\"; _ga=GA1.3.1815051801.1473401208; tt_webid=28215330997; Hm_lvt_773f1a5aa45c642cf87eef671e4d3f6a=1480736686; Hm_lpvt_773f1a5aa45c642cf87eef671e4d3f6a=1480736686; skip_guidence=1; csrftoken=783a7367ddad6fa93161b76e649a59c0" )
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
        logger.info( "爬取到数据");
        List<TouTiaoNewModel> newsList = getNewsList( page );
        logger.info( "分析数据");
        for (TouTiaoNewModel touTiaoNewModel : newsList) {
            //保存新闻
            logger.info( "保存一条新闻:  标题{}", JsonTool.writeValueAsString( touTiaoNewModel.getTitle() ) );
            touTiaoYiChangDetailProcessor.Run( touTiaoNewModel, touTiaoYiChangDetailProcessor );
        }

    }

    public List<TouTiaoNewModel> getNewsList(Page page) {
        String json = page.getRawText();
        JSONObject jsonBean = JSONObject.fromObject( json );
        Object data = (JSONArray) jsonBean.get( "data" );
        JSONArray jsonArray = JSONArray.fromObject( data );
        List<TouTiaoNewModel> touTiaoNewModels = new ArrayList<TouTiaoNewModel>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get( i );
            String title = (String) jsonObject.get( "title" );
            String source = (String) jsonObject.get( "source" );
            String media_name = (String) jsonObject.get( "media_name" );
            String dateTime = (String) jsonObject.get( "dateTime" );
            String desc = (String) jsonObject.get( "abstract" );
            Boolean has_video = (Boolean) jsonObject.get( "has_video" );
            String url = (String) jsonObject.get( "item_source_url" );
            url = "https://www.toutiao.com" + url;
            JSONArray image_list = (JSONArray) jsonObject.get( "image_list" );
            TouTiaoNewModel touTiaoNewModel = new TouTiaoNewModel();
            touTiaoNewModel.setDateTime( dateTime );
            touTiaoNewModel.setMedia_name( media_name );
            touTiaoNewModel.setSource( source );
            touTiaoNewModel.setTitle( title );
            touTiaoNewModel.setDesc( desc );
            touTiaoNewModel.setUrl( url );
            touTiaoNewModel.setHasVideo( has_video );
            List<YFile> yFiles = new ArrayList<YFile>();
            for (int j = 0; j < image_list.size(); j++) {
                YFile yFile = new YFile();
                JSONObject fileObject = (JSONObject) image_list.get( j );
                yFile.setUrl( (String) fileObject.get( "url" ) );
                yFile.setIsLocalFile( 0 );
                boolean add = yFiles.add( yFile );
            }
            touTiaoNewModel.setYFiles( yFiles );
            touTiaoNewModels.add( touTiaoNewModel );

        }
        System.out.println( JsonTool.writeValueAsString( touTiaoNewModels ) );
        return touTiaoNewModels;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(TouTiaoYiChangPageProcessor githubRepoPageProcessor, String url) {
        Spider.create( githubRepoPageProcessor ).addUrl( url ).thread( 1 ).run();
    }

    public static void main(String[] args) {
        Long dateTime = new Date().getTime();
        Spider.create( new TouTiaoYiChangPageProcessor() ).addUrl( "http://www.toutiao.com/search_content/?offset=60&format=json&keyword=%E5%AE%9C%E6%98%8C&autoload=true&count=1&_=" + dateTime ).thread( 1 ).run();
    }
}