package com.rdx.newsSOA.spider;

import com.rdx.newsSOA.common.UrlEnum;
import com.rdx.newsSOA.common.constants.SessionConstants.CookieConstants;
import com.rdx.newsSOA.dto.BuildingSeachRequest;
import com.rdx.newsSOA.dto.EstateSearchRequest;
import com.rdx.newsSOA.dto.EstateSearchResponse;
import com.rdx.newsSOA.face.EstateService;
import com.rdx.newsSOA.util.HttpClientUtil;
import com.rdx.newsSOA.util.JsonTool;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;

@Component("estateListProcessor")
@Scope("prototype")
public class EstateListProcessor implements PageProcessor {
    private EstateService estateService;

    public void setEstateService(EstateService estateService){
        this.estateService = estateService;
    }

    private Site site = Site.me().setCycleRetryTimes( 5 ).setRetryTimes( 5 ).setSleepTime( 500 ).setTimeOut( 3 * 60 * 1000 )
            .setUserAgent( "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36" )
            .addHeader( "Accept", "text/javascript, text/html, application/xml, text/xml, */*" )
            .addHeader( "Accept-Encoding", "gzip, deflate, sdch, br" )
            .addHeader( "Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6" )
            .addHeader( "Cache-Control", "no-cache" )
            .addHeader( "Connection", "keep-alive" )
//            .addCookie( "jrbqiantai", getCookieMap().get( "jrbqiantai" ) )
            .addCookie( "jrbqiantai", CookieConstants.jrbqiantai )
//            .addCookie( "login_Account", "18516285363" )
            .addCookie( "rememberme", "1" )
//            .addCookie( "8a2db3a95b42909b015b47b7e4100ef1", "%u4E73%u5C71%u4E8C%u6751_%u4E0A%u6D77" )
            .addCookie( "Hm_lvt_203904e114edfe3e6ab6bc0bc04207cd", String.valueOf( new Date().getTime() ) )
            .addCookie( "Hm_lpvt_203904e114edfe3e6ab6bc0bc04207cd", String.valueOf( new Date().getTime() ) )
//            .addCookie( "loginCity_JR_8a2db3a95b42909b015b47b7e4100ef1", "上海" )
//            .addCookie( "gjdx", "%e6%b6%a6%e6%b1%9f%e8%8a%b1%e8%8b%91" )
            .addHeader( "Host", "www.fungugu.com" )
            .addHeader( "Pragma", "no-cache" )
            //.addHeader( "Referer", "http://www.fungugu.com/JinRongGuZhi/toJinRongGuZhi_s?xqmc=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&gjdx=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&residentialName=%E6%B6%A6%E6%B1%9F%E8%8A%B1%E8%8B%91&dz=&xzq=%E6%9D%BE%E6%B1%9F%E5%8C%BA&xqid=14126&ldid=406657&dyid=&hid=&loudong=&danyuan=&hu=&retrievalMethod=%E6%88%BF%E6%9C%AC%E6%A3%80%E7%B4%A2" )
            .addHeader( "X-Requested-With", "XMLHttpRequest" )
            .setCharset( "UTF-8" );

    @Override
    public void process(Page page) {
        String json = page.getRawText();
        JSONObject jsonBean = JSONObject.fromObject( json );
        JSONArray dataArray = (JSONArray) jsonBean.get( "data" );
        List<EstateSearchResponse> estateSearchResponsesList = new ArrayList<>();
        Request request = page.getRequest();
        EstateSearchRequest estateSearchRequest = (EstateSearchRequest) request.getExtra( "EstateSearchRequest" );
        for (Object object : dataArray) {
            EstateSearchResponse estateSearchResponse = (EstateSearchResponse) JSONObject.toBean( (JSONObject.fromObject( object )), EstateSearchResponse.class );
            estateSearchResponsesList.add( estateSearchResponse );
        }
        for (EstateSearchResponse estateSearchResponse : estateSearchResponsesList) {
            estateSearchResponse.setEstateName( estateSearchRequest.getEstateName() );
            estateSearchResponse.setFggEstateId( estateSearchResponse.getResidentialareaId() );
            estateSearchResponse.setCityName( estateSearchRequest.getCityName() );
            estateSearchResponse.setEstateId( estateSearchRequest.getEstateId() );
            estateService.insertEstate(estateSearchResponse);

            //去查询楼栋单元信息
            BuildingSeachRequest buildingSeachRequest = new BuildingSeachRequest();
            buildingSeachRequest.setId( estateSearchResponse.getResidentialareaId() );
            buildingSeachRequest.setCity( estateSearchRequest.getCityName() );
            buildingSeachRequest.setEstateId( estateSearchRequest.getEstateId() );
            estateService.spiderGggBuildInfo( buildingSeachRequest );
        }
        System.out.printf( "" + JsonTool.writeValueAsString( estateSearchResponsesList ) );
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 异步处理
     * @param estateSearchRequest
     * @param estateListProcessor
     */
    public void getEstateList(EstateSearchRequest estateSearchRequest, EstateListProcessor estateListProcessor) {
        long time = new Date().getTime();
        Map<String, String> map = new HashMap<>();
        Map<String,Object> extraMap = new HashMap<>(  );
        map.put( "q", estateSearchRequest.getEstateName() );
        map.put( "limit", "150" );
        map.put( "v", estateSearchRequest.getAreaName() );
        map.put( "city", estateSearchRequest.getCityName() );
        map.put( "timestamp", "" + time );
        map.put( "accurateType", "1" );
        map.put( "matchType", "1" );
        String url = HttpClientUtil.getUrl( UrlEnum.ESTATE_LIST_URL, map );
        extraMap.put( "EstateSearchRequest", estateSearchRequest);
        Request request = new Request();
        request.setExtras( extraMap );
        request.setUrl( url );
        Spider.create( estateListProcessor ).addRequest( request ).thread( 1 ).run();
    }


    public static void main(String[] args) {
        EstateSearchRequest estateSearchRequest = new EstateSearchRequest();
        estateSearchRequest.setAreaName( "润江花苑" );
        estateSearchRequest.setCityName( "上海" );
        estateSearchRequest.setEstateName( "润江花苑" );
        EstateListProcessor estateListProcessor = new EstateListProcessor();
        estateListProcessor.getEstateList( estateSearchRequest, estateListProcessor );
    }
}