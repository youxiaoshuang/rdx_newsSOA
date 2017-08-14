package com.rdx.newsSOA.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpUtil {

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpUtil http = new HttpUtil();
        System.out.println( "\nTesting 2 - Send Http POST request" );
        Map<String, String> heads = new HashMap<>();
        Map<String, String> params = new HashMap();
        heads.put( "Authorization", "Bearer 1aff5a07-dfe6-4b18-ae96-34f6c387f754" );
        params.put( "cid", "7" );
        params.put( "title", "国际奥委会发话：中国是超级大国，理应主动举办奥运会！" );
        params.put( "content", "<div class=\"article-content\"><div><p>2001年7月13日，这是个令全中国人民都欣喜的日子，因为我们终于成功的得到了2008年奥运会的举办权。1991年，中国申请奥运会距离成功仅差两票，1999年，中国再次申办奥运会，终于经过两年的努力，中国北京获得了第29届北京奥运会的申办权。</p><p><img src=\"http://p1.pstatp.com/large/31fe00000b53076e2f28\" img_width=\"633\" img_height=\"374\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p>2008年夏天，期待已久的北京奥运会终于开幕。许多外国运动员以及训练员都见到了中国文化的博大精深以及中国的强大。2008年的北京奥运会举办的是相当成功的，中国的文化得到了良好的展现。2008年，是国人欢庆的一年，同时也是悲伤的一年，因为那年发生的不仅仅是奥运会，还有汶川大地震，这场地震导致许许多多的同胞都再也没有与亲人团聚。</p><p><img src=\"http://p1.pstatp.com/large/31f30004e8e242c4efdd\" img_width=\"591\" img_height=\"384\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p>转眼间已经到了2017年，将近十年的时间已经过去了，关于申办奥运会的名单上，中国再次上榜。2024年和2028年的承办国家都已经定下，但是这并不是申办的，而是国际奥委会的赞助。奥运会花费数量想当巨大的资金，国际奥委会承诺会赞助美国120亿元，用来举办2028年的美国洛杉矶奥运会。</p><p><img src=\"http://p1.pstatp.com/large/31f30004e8784b818cb4\" img_width=\"629\" img_height=\"360\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p><img src=\"http://p1.pstatp.com/large/3202000008d7d1f85f06\" img_width=\"635\" img_height=\"333\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p>那么2032年呢？哪个国家申办了呢？罗马和布达佩斯分别都退出了申办，主要原因还是经济和场馆用途。还记得巴西里约奥运会吗？如今已经是场馆废弃，巨额债务！这是一个失败的例子。</p><p><img src=\"http://p3.pstatp.com/large/320200000893bb777265\" img_width=\"635\" img_height=\"336\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p>近日，国际奥委会发表了一条消息，他们希望中国可以承办2032年的奥运会。国际奥委会主席巴赫发表了自己的看法，他说：“以后的奥运会可能会从申办制改为指定制，经济雄厚的大国家需要承办这样的运动项目，就像中国这样的超级大国，理应主动申请举办奥运会。”</p><p><img src=\"http://p3.pstatp.com/large/31f7000017a17c069950\" img_width=\"631\" img_height=\"399\" alt=\"官宣！国际奥委会发话：中国是超级大国，理应主动举办奥运会！\" inline=\"0\"></p><p>从申办奥运会变成指定，作为超级大国的中国到底该不该接手2032年的奥运会承办权？要知道美国可是被赞助了120个亿，不知各位读者如何看待这件事情！</p></div></div>\n" );
        String url = "http://www.xn--0sq05uutk.com/api/v1/topics";
        http.sendPost( url, params, heads );

    }

    // HTTP GET请求
    public void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=developer";

        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet( url );

        //添加请求头
        request.addHeader( "User-Agent", USER_AGENT );

        HttpResponse response = client.execute( request );

        System.out.println( "\nSending 'GET' request to URL : " + url );
        System.out.println( "Response Code : " +
                response.getStatusLine().getStatusCode() );

        BufferedReader rd = new BufferedReader(
                new InputStreamReader( response.getEntity().getContent() ) );

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append( line );
        }

        System.out.println( result.toString() );

    }

    // HTTP POST请求
    public String sendPost(String url, Map<String, String> params, Map<String, String> heads) throws Exception {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new UTF8PostMethod( url );
        //添加请求头
        postMethod.addRequestHeader( "User-Agent", USER_AGENT );
        postMethod.addRequestHeader( "Content-Type", "application/x-www-form-urlencoded; charset=UTF-8" );
        for (String s : heads.keySet()) {
            postMethod.addRequestHeader( s, heads.get( s ) );
        }
        for (String s : params.keySet()) {
            postMethod.addParameter( s, params.get( s ) );
        }
        int i = httpClient.executeMethod( postMethod );
        String responseBodyAsString = postMethod.getResponseBodyAsString();
        System.out.println( "\nSending 'POST' request to URL : " + url );
        System.out.println( "Post parameters : " + postMethod.getParams() );
        System.out.println( "Response Code : " + i );

        return responseBodyAsString;

    }

    public static class UTF8PostMethod extends PostMethod {
        public UTF8PostMethod(String url) {
            super( url );
        }

        @Override
        public String getRequestCharSet() {
            //return super.getRequestCharSet();
            return "UTF-8";
        }
    }


}