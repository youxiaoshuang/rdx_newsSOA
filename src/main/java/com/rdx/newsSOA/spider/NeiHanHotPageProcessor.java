package com.rdx.newsSOA.spider;


import com.rdx.newsSOA.dao.TouTiaoNewModel;
import com.rdx.newsSOA.entity.NDoucument;
import com.rdx.newsSOA.face.NewsService;
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

public class NeiHanHotPageProcessor implements PageProcessor {
    @Autowired
    private NewsService newsService;

    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .setUserAgent( "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1" )
            .addHeader( "Accept", "*/*" )
            .addHeader( "Accept-Encoding", "gzip, deflate, sdch" )
            .addHeader( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" )
            .addCookie( "uuid", "w:f4fda41206df471a90c2988bb4b138b4" )
            .addCookie( "_ga", "GA1.3.1815051801.1473401208" )
            .addCookie( "tt_webid", "28215330997" )
            .addCookie( "Hm_lvt_773f1a5aa45c642cf87eef671e4d3f6a", "1480736686,1480920247" )
            .addCookie( "skip_guidence", "1" )
            .addCookie( "csrftoken", "783a7367ddad6fa93161b76e649a59c0" )
            .addHeader( "Host", "m.neihanshequ.com" )
            .addHeader( "Pragma", "no-cache" )
            .addHeader( "Referer", "http://m.neihanshequ.com/?skip_guidence=1" )
            .addHeader( "Pragma", "no-cache" )
            .addHeader( "X-Requested-With", "XMLHttpRequest" )
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
        logger.info( "爬取到的数据{}", JsonTool.writeValueAsString( page.getRawText() ) );
        List<TouTiaoNewModel> newsList = getNewsList( page );
//        logger.info( "分析之后的数据：{}", JsonTool.writeValueAsString( newsList ) );
//        for (TouTiaoNewModel touTiaoNewModel : newsList) {
//            //保存新闻
//            logger.info( "保存一条新闻:{}", JsonTool.writeValueAsString( touTiaoNewModel ) );
//            neiHanHotDetailProcessor.Run( touTiaoNewModel, neiHanHotDetailProcessor );
//        }

    }

    public List<TouTiaoNewModel> getNewsList(Page page) {
        String json = page.getRawText();
        JSONObject jsonBean = JSONObject.fromObject( json );
        Object data = jsonBean.get( "data" );
        Object next = jsonBean.get( "next" );
        JSONArray jsonArray = JSONArray.fromObject( data );
        JSONArray nextArray = JSONArray.fromObject( next );
        JSONArray dataArray = jsonArray.getJSONObject( 0 ).getJSONArray( "data" );
        List<TouTiaoNewModel> touTiaoNewModels = new ArrayList<TouTiaoNewModel>();
        for (int i = 0; i < dataArray.size(); i++) {
            TouTiaoNewModel touTiaoNewModel = new TouTiaoNewModel();
            JSONObject groupObject = (JSONObject) dataArray.get( i );
            JSONObject group = groupObject.getJSONObject( "group" );
            String title = (String) group.get( "text" );
            String createTime = String.valueOf( group.get( "create_time" ) );
            String share_url = (String) group.get( "share_url" );
            String content = (String) group.get( "content" );
            touTiaoNewModel.setContent( content );
            touTiaoNewModel.setTitle( title );
            touTiaoNewModel.setDateTime( createTime );
            touTiaoNewModel.setDesc( content );
            touTiaoNewModel.setSourceTye( 5 );
            touTiaoNewModel.setUrl( share_url );
            NDoucument nDoucument = newsService.addTTNews( touTiaoNewModel );
        }
        System.out.println( JsonTool.writeValueAsString( touTiaoNewModels ) );
        return touTiaoNewModels;
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(NeiHanHotPageProcessor neiHanHotPageProcessor, String url) {
        Spider.create( neiHanHotPageProcessor ).addUrl( url ).thread( 1 ).run();
    }

    public static void main(String[] args) {
        Long dateTime = new Date().getTime();
        Spider.create( new NeiHanHotPageProcessor() ).addUrl( "http://m.neihanshequ.com/?is_json=1&app_name=neihanshequ_web&min_time=1480736703&csrfmiddlewaretoken=783a7367ddad6fa93161b76e649a59c0" ).thread( 1 ).run();
    }
}