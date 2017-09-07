package com.rdx.newsSOA.spider;

import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class JunShiListProcessor implements PageProcessor {
    @Autowired
    private JunShiDetailProcessor junShiDetailProcessor;

    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 ).setTimeOut( 10000 );

    @Override
    public void process(Page page) {
        page.addTargetRequests( page.getHtml().links().regex( "(http://mil.eastday.com/a/.*)" ).all() );
        List<Request> targetRequests = page.getTargetRequests();
        String[] urlss = new String[targetRequests.size()];
        for (int i = 0; i < urlss.length; i++) {
            urlss[i] = targetRequests.get( i ).getUrl();
        }
        Spider.create( junShiDetailProcessor ).addUrl( urlss ).thread( 2 ).run();

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(JunShiListProcessor duoWeiListProcessor){
        Spider.create( duoWeiListProcessor ).addUrl( "http://mil.eastday.com/" ).thread( 1 ).run();
    }
    public static void main(String[] args) {
    }
}