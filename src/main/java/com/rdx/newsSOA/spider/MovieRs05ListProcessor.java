package com.rdx.newsSOA.spider;

import org.springframework.beans.factory.annotation.Autowired;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieRs05ListProcessor implements PageProcessor {
    @Autowired
    private MovieRs05DetailProcessor movieRs05DetailProcessor;

    private Site site = Site.me().setRetryTimes( 3 ).setSleepTime( 1000 ).setTimeOut( 10000 );

    @Override
    public void process(Page page) {
        page.addTargetRequests( page.getHtml().links().regex( "(http://www.rs05.com/movie.*.html)" ).all() );
        List<Request> targetRequests = page.getTargetRequests();
        String[] urlss = new String[targetRequests.size()];
        for (int i = 0; i < urlss.length; i++) {
            urlss[i] = targetRequests.get( i ).getUrl();
        }
        //去重
        Set<String> set = new HashSet<>();
        for(int i=0;i<urlss.length;i++){
            set.add(urlss[i]);
        }
        String[] arrayResult = (String[]) set.toArray(new String[set.size()]);
        Spider.create( movieRs05DetailProcessor ).addUrl( arrayResult ).thread( 1 ).run();

    }

    @Override
    public Site getSite() {
        return site;
    }

    public void run(MovieRs05ListProcessor movieRs05ListProcessor){
        String[] strs = new String[50];
        for (int i=strs.length-1;i>=0;i--){
            strs[i] = "http://www.rs05.com/movie/?p="+i+1+"&o=1";
        }
        Spider.create( movieRs05ListProcessor ).addUrl(strs).thread( 1 ).run();
    }
    public static void main(String[] args) {
        MovieRs05ListProcessor movieRs05ListProcessor = new MovieRs05ListProcessor();

        movieRs05ListProcessor.run( movieRs05ListProcessor );
    }
}