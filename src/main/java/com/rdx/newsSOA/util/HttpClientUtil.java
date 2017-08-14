package com.rdx.newsSOA.util;

import com.rdx.newsSOA.common.UrlEnum;
import com.rdx.newsSOA.common.constants.SessionConstants.CookieConstants;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by youxiaoshuang on 2017/4/10.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
public class HttpClientUtil{

    public static String map2paramStr(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        int i = 0;
        StringBuffer stringBuffer = new StringBuffer( "" );
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (i == 0) {
                stringBuffer.append( "?" + stringStringEntry.getKey() + "=" + stringStringEntry.getValue() );
            } else {
                stringBuffer.append( "&" + stringStringEntry.getKey() + "=" + stringStringEntry.getValue() );
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static String getUrl(UrlEnum urlEnum, Map param) {
        return urlEnum.getUrl() + map2paramStr( param );
    }

    public static String getCookieByURL(String base_url) {
        String url = base_url;
        StringBuilder json = new StringBuilder();
        String result = "";
        String cookieVal = "";
        try {
            URL u = new URL( url );
            HttpURLConnection uc = (HttpURLConnection) u.openConnection();
            uc.setRequestMethod( "GET" );
            //uc.setRequestMethod("POST");
            cookieVal = uc.getHeaderField( "Set-Cookie" );    //获取session
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return cookieVal;
    }

    public static String getJrbqiantai() {
        String cookieByURL = getCookieByURL( getUrl( UrlEnum.XUNJIASHISHIJILU_NOLOGIN_URL, null ) );
        String[] split = cookieByURL.split( ";" );
        String s = split[0];
        return s.substring( s.indexOf( "=" ) + 1 );
    }

    public static void setJrbqiantai() {
            String jrbqiantai = getJrbqiantai();
            Map<String, String> cookieMap = new HashMap<>();
            cookieMap.put( "jrbqiantai", jrbqiantai );
            CookieConstants.setCookieMap( cookieMap );
    }


    public static void main(String[] args) {
        setJrbqiantai();
        String jrbqiantai = CookieConstants.getCookieMap().get( "jrbqiantai" );
        System.out.printf( ""+jrbqiantai );
    }

}
