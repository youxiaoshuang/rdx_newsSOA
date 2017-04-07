package com.rdx.newsSOA.face.impl;

import com.rdx.newsSOA.face.ChatService;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by youxiaoshuang on 2016/10/17.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "chatService")
public class ChatServiceImpl implements ChatService{
    private CloseableHttpClient httpclient = HttpClients.createDefault();
    private String url = "http://simsimi.com/getRealtimeReq?lc=zh&status=W";
    private String uuid = "b0gT5X36sDQFwT7LLZH4cc3rcyr2";
    private String ft = "1.0";
    private Logger logger = LoggerFactory.getLogger( this.getClass() );
    public String chat(String msg) {
        logger.info( "传入的参数:"+msg );
        url+="&reqText="+msg;
        url+="&uuid="+uuid;
        url+="&ft="+ft;
        logger.info( "传入的url："+url );
        String s = doGet( url );
        logger.info( "小黄鸡返回:"+s );
        JSONObject json = JSONObject.fromObject( s );
        s = json.getString( "respSentence" );
        System.out.println(s);
        return s;
    }

    public String doGet(String url)
    {
        String result= "";
//      HttpGet httpRequst = new HttpGet(URI uri);
//      HttpGet httpRequst = new HttpGet(String uri);
//      创建HttpGet或HttpPost对象，将要请求的URL通过构造方法传入HttpGet或HttpPost对象。
        HttpGet httpRequst = new HttpGet(url);
        httpRequst.setHeader( "Host","simsimi.com" );
        httpRequst.setHeader( "Connection","keep-alive" );
        httpRequst.setHeader( "Pragma","no-cache" );
        httpRequst.setHeader( "Cache-Control","no-cache" );
        httpRequst.setHeader( "Accept","application/json, text/javascript, */*; q=0.01" );
        httpRequst.setHeader( "X-Requested-With","XMLHttpRequest" );
        httpRequst.setHeader( "User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X)AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1" );
        httpRequst.setHeader( "Content-Type","application/json; charset=utf-8" );
        httpRequst.setHeader( "Referer","http://simsimi.com/storygame/main" );
//        httpRequst.setHeader( "Accept-Encoding","gzip, deflate, sdch" );
        httpRequst.setHeader( "Accept-Language","zh-CN,zh;q=0.8,en;q=0.6" );
        httpRequst.setHeader( "Cookie","lang=zh_CN; uuid=b0gT5X36sDQFwT7LLZH4cc3rcyr2; _ga=GA1.2.573593215.1476690346; _gat=1; currentChatCnt=1" );

//      new DefaultHttpClient().execute(HttpUriRequst requst);
        try {
            //使用DefaultHttpClient类的execute方法发送HTTP GET请求，并返回HttpResponse对象。
            HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequst);//其中HttpGet是HttpUriRequst的子类
            if(httpResponse.getStatusLine().getStatusCode() == 200)
            {
                HttpEntity httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);//取出应答字符串
                // 一般来说都要删除多余的字符
                result.replaceAll("\r", "");//去掉返回结果中的"\r"字符，否则会在结果字符串后面显示一个小方格
            }
            else
                httpRequst.abort();
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result = e.getMessage().toString();
        }
        return result;
    }



    public static void main(String[] args) {
        new ChatServiceImpl().chat( "你多大" );
    }
}
