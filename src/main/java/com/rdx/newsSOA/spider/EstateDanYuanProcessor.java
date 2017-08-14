package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.common.UrlEnum;
import com.rdx.newsSOA.common.constants.SessionConstants.CookieConstants;
import com.rdx.newsSOA.dto.DanYuanSeachRequest;
import com.rdx.newsSOA.dto.DanYuanSeachResponse;
import com.rdx.newsSOA.dto.RoomSeachRequest;
import com.rdx.newsSOA.dto.RoomSeachResponse;
import com.rdx.newsSOA.face.EstateService;
import com.rdx.newsSOA.util.HttpClientUtil;
import com.rdx.newsSOA.util.JsonTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;
@Component("estateDanYuanProcessor")
@Scope("prototype")
public class EstateDanYuanProcessor implements PageProcessor {

    private EstateService estateService;
    public void setEstateService(EstateService estateService){
        this.estateService = estateService;
    }
    private Logger logger = LoggerFactory.getLogger( this.getClass() );

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .setUserAgent( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36" )
            .addHeader( "Accept", "text/javascript, text/html, application/xml, text/xml, */*" )
            .addHeader( "Accept-Encoding", "gzip, deflate, sdch, br" )
            .addHeader( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" )
            .addHeader( "Cache-Control", "no-cache" )
            .addHeader( "Connection", "keep-alive" )
            .addCookie( "jrbqiantai", CookieConstants.jrbqiantai )
//            .addCookie( "login_Account", "18516285363" )
            .addCookie( "rememberme", "1" )
            .addCookie( "Hm_lvt_203904e114edfe3e6ab6bc0bc04207cd", "1491558121" )
            .addCookie( "Hm_lpvt_203904e114edfe3e6ab6bc0bc04207cd", String.valueOf( new Date().getTime() ) )
            .addCookie( "loginCity_JR_8a2db3a95b42909b015b47b7e4100ef1", "上海" )
            .addCookie( "gjdx", "%e6%b6%a6%e6%b1%9f%e8%8a%b1%e8%8b%91" )
            .addHeader( "Host", "www.fungugu.com" )
            .addHeader( "Pragma", "no-cache" )
            //.addHeader( "Referer", "http://www.fungugu.com/JinRongGuZhi/toJinRongGuZhi_s?xqmc=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&gjdx=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&residentialName=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&dz=&xzq=%E6%9D%BE%E6%B1%9F%E5%8C%BA&xqid=14126&ldid=406657&dyid=&hid=&loudong=&danyuan=&hu=&retrievalMethod=%E6%88%BF%E6%9C%AC%E6%A3%80%E7%B4%A2" )
            .addHeader( "X-Requested-With", "XMLHttpRequest" )
            .setCharset( "UTF-8" );

    @Override
    public void process(Page page) {
        String json = page.getRawText();
        List<DanYuanSeachResponse> danYuanSeachResponses;
        JSONObject jsonBean = JSONObject.fromObject( json );
        JSONArray dataArray = (JSONArray) jsonBean.get( "list" );
        DanYuanSeachRequest danYuanSeachRequest = (DanYuanSeachRequest) page.getRequest().getExtra( "DanYuanSeachRequest" );
        danYuanSeachResponses = new ArrayList<>();
        for (Object object : dataArray) {
            DanYuanSeachResponse danYuanSeachResponse = (DanYuanSeachResponse) JSONObject.toBean( (JSONObject.fromObject( object )), DanYuanSeachResponse.class );
            danYuanSeachResponses.add( danYuanSeachResponse );
        }
        for (DanYuanSeachResponse danYuanSeachResponse : danYuanSeachResponses) {
            String type = danYuanSeachResponse.getType();
            if(type.equals( "danyuan" )){
                danYuanSeachResponse.setDanYuanId( danYuanSeachResponse.getId() );
                danYuanSeachResponse.setDanYuanName( danYuanSeachResponse.getName() );
                danYuanSeachResponse.setBuildId( danYuanSeachRequest.getBuildId() );
                danYuanSeachResponse.setId( null );
                estateService.insertDanYuan( danYuanSeachResponse );
                RoomSeachRequest roomSeachRequest = new RoomSeachRequest();
                roomSeachRequest.setCity( danYuanSeachRequest.getCity() );
                roomSeachRequest.setId( danYuanSeachResponse.getDanYuanId() );
                roomSeachRequest.setType( danYuanSeachResponse.getType() );
                roomSeachRequest.setDanYuanId( danYuanSeachResponse.getDanYuanId() );
                estateService.spiderGggRoomInfo( roomSeachRequest );
            }else{
                RoomSeachResponse roomSeachResponse = new RoomSeachResponse();
                roomSeachResponse.setRoomId( danYuanSeachResponse.getId() );
                roomSeachResponse.setRoomName( danYuanSeachResponse.getName() );
                roomSeachResponse.setBuildId( danYuanSeachRequest.getBuildId() );
                roomSeachResponse.setType( danYuanSeachResponse.getType() );
                roomSeachResponse.setName( danYuanSeachResponse.getName() );
                estateService.insertRoom(roomSeachResponse);
            }
        }
        System.out.printf( "" + JsonTool.writeValueAsString( danYuanSeachResponses ) );
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void getDanYuanList(DanYuanSeachRequest danYuanSeachRequest, EstateDanYuanProcessor danYuanProcessor) {
        long time = new Date().getTime();
        Map<String, String> map = new HashMap<>();
        Map<String,Object> extraMap = new HashMap<>(  );
        map.put( "city", danYuanSeachRequest.getCity() );
        map.put( "id", String.valueOf( danYuanSeachRequest.getId() ) );
        map.put( "type", danYuanSeachRequest.getType() );
        String url = HttpClientUtil.getUrl( UrlEnum.BUILD_LIST_URL, map );
        extraMap.put( "DanYuanSeachRequest", danYuanSeachRequest);
        Request request = new Request();
        request.setExtras( extraMap );
        request.setUrl( url );
        Spider.create( danYuanProcessor ).addRequest( request ).thread( 1 ).run();
    }


    public static void main(String[] args) {

    }
}