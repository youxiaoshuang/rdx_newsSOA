package com.rdx.newsSOA.face.impl;


import com.rdx.newsSOA.face.SimSimiService;
import com.rdx.newsSOA.face.WechatService;
import com.rdx.newsSOA.util.MessageUtil;
import com.rdx.newsSOA.weixin.req.BaseMessageRequest;
import com.rdx.newsSOA.weixin.resp.Article;
import com.rdx.newsSOA.weixin.resp.TextMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by youxiaoshuang on 2016/10/17.
 * Projiect fightting
 * Author youxiaoshuang
 */
@Service(value = "wechatService")
public class WechatServiceImpl implements WechatService {
    @Autowired
    private SimSimiService simSimiService;
    @Autowired
    private ChatServiceImpl chatService;
    @Value( "#{sys['httpPath']}" )
    private String httpPath;

    Logger logger = LoggerFactory.getLogger( this.getClass() );

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return
     */
    public String processRequest(HttpServletRequest request) {
        // 默认返回的文本消息内容
        String respContent = "请求处理异常，请稍候尝试！";
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.parseXml( request );
            BaseMessageRequest baseMessageRequest = MessageUtil.fetchBaseMessageRequest( requestMap ); // 获取微信的基本信息
            String fromUserName = baseMessageRequest.getFromUserName();                  // 发送方帐号（open_id）
            String toUserName = baseMessageRequest.getToUserName();                      // 开发者微信号
            String msgType = baseMessageRequest.getMsgType();                            // 消息类型（text/image/location/link）
            String content = requestMap.get( "Content" );                                  // 消息内容
            String eventType = requestMap.get( "Event" );                                  // 事件类型
            logger.info( "processRequest requestMap:" + requestMap );
            logger.info( "用户ID" + fromUserName );
            logger.info( "公众号ID" + toUserName );
            // 文本消息
            if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_TEXT )) {
                //小黄金智能对话
                respContent = processSimMsg( requestMap, baseMessageRequest );
            }
            // 图片消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_IMAGE )) {
                respContent = processDefaultMsg( fromUserName, toUserName );
            }
            // 地理位置消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_LOCATION )) {
                respContent = processDefaultMsg( fromUserName, toUserName );
            }
            // 链接消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_LINK )) {
                respContent = processDefaultMsg( fromUserName, toUserName );
            }
            // 音频消息
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_VOICE )) {
                respContent = processDefaultMsg( fromUserName, toUserName );
            }
            // 事件推送
            else if (msgType.equals( MessageUtil.REQ_MESSAGE_TYPE_EVENT )) {
                logger.info( "事件推送" );
                // 用户未关注时，进行关注后的事件推送
                if (eventType.equals( MessageUtil.EVENT_TYPE_SUBSCRIBE )) {//处理关注成功
                    respContent = processDefaultMsg( fromUserName, toUserName );
                } else if (eventType.equals( MessageUtil.RESP_MESSAGE_TYPE_SCAN )) {//用户已关注时的事件推送
                    respContent = processMsg( "您已经是日大侠会员了", fromUserName, toUserName );
                } else if (eventType.equals( MessageUtil.EVENT_TYPE_UNSUBSCRIBE )) { //取消订阅
                    respContent = processDefaultMsg( fromUserName, toUserName );
                } else if (eventType.equals( MessageUtil.EVENT_TYPE_VIEW )) {
                    respContent = processDefaultMsg( fromUserName, toUserName );
                } else if (eventType.equals( MessageUtil.EVENT_TYPE_CLICK )) {// 自定义菜单点击事件
                    respContent = click( requestMap, baseMessageRequest );
                } else {
                    respContent = processDefaultMsg( fromUserName, toUserName );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error( "Exception while processRequest: ", e );
        }
        return respContent;
    }


    /**
     * 自定义发送微信回执消息
     *
     * @param msg
     * @param fromUserName
     * @param toUserName
     * @return
     * @throws Exception
     */

    public String processMsg(String msg, String fromUserName, String toUserName) {
        TextMessageResponse textMessage = new TextMessageResponse();
        textMessage.setToUserName( fromUserName );
        textMessage.setFromUserName( toUserName );
        textMessage.setCreateTime( new Date().getTime() );
        textMessage.setMsgType( MessageUtil.RESP_MESSAGE_TYPE_TEXT );
        textMessage.setFuncFlag( 0 );
        textMessage.setContent( msg ); //默认为空,不会发送任何信息.
        return MessageUtil.textMessageToXml( textMessage );
    }

    /**
     * 回复空消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public String processNullMsg(String fromUserName, String toUserName) {
        return "";
    }

    /**
     * 回复默认的消息
     *
     * @param fromUserName
     * @param toUserName
     * @return
     */
    public String processDefaultMsg(String fromUserName, String toUserName) {
        String s = "欢迎来到日大侠";
        return processArticle( fromUserName,  toUserName,  s);
    }

    /**
     * 小黄鸡智能对话
     *
     * @param requestMap
     * @param baseMessageRequest
     * @return
     */
    public String processSimMsg(Map<String, String> requestMap, BaseMessageRequest baseMessageRequest) {
        String fromUserName = baseMessageRequest.getFromUserName();                  // 发送方帐号（open_id）
        String toUserName = baseMessageRequest.getToUserName();                      // 开发者微信号
        String content = requestMap.get( "Content" );
        // 默认返回的文本消息内容
        String respContent = "请求处理异常，请稍候尝试！";
        // 回复文本消息
        TextMessageResponse textMessage = new TextMessageResponse();
        textMessage.setToUserName( fromUserName );
        textMessage.setFromUserName( toUserName );
        textMessage.setCreateTime( new Date().getTime() );
        textMessage.setMsgType( MessageUtil.RESP_MESSAGE_TYPE_TEXT );
        textMessage.setFuncFlag( 0 );
        String s = new ChatServiceImpl().chat( content );
        return processArticle( fromUserName,  toUserName,  s);
    }

    /**
     * 自定义发送微信回执模板(不带图片)
     *
     * @param fromUserName
     * @param toUserName
     * @param title
     * @return
     */
    public String processArticle(String fromUserName, String toUserName, String title) {
        return processArticle(fromUserName,toUserName,title,"点击进入日大侠，惊喜多多",httpPath);
    }

    /**
     * 自定义发送微信回执模板(不带图片)
     *
     * @param fromUserName
     * @param toUserName
     * @param title
     * @param description
     * @param url
     * @return
     */
    public String processArticle(String fromUserName, String toUserName, String title, String description, String url) {
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle( title );
        article.setDescription( description );
        article.setUrl( url );
        articleList.add( article );
        return MessageUtil.ResNewsMessage( fromUserName, toUserName, articleList );
    }

    /**
     * 自定义发送微信回执模板(带图片)
     *
     * @param fromUserName
     * @param toUserName
     * @param title
     * @param description
     * @param url
     * @param picUrl
     * @return
     */
    public String processArticle(String fromUserName, String toUserName, String title, String description, String url, String picUrl) {
        List<Article> articleList = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle( title );
        article.setDescription( description );
        article.setPicUrl( picUrl );
        article.setUrl( url );
        articleList.add( article );
        return MessageUtil.ResNewsMessage( fromUserName, toUserName, articleList );
    }


    public String click(Map<String, String> requestMap, BaseMessageRequest baseMessageRequest) throws Exception {
        String fromUserName = baseMessageRequest.getFromUserName();                  // 发送方帐号（open_id）
        String toUserName = baseMessageRequest.getToUserName();                      // 开发者微信号
        String msgType = baseMessageRequest.getMsgType();                            // 消息类型（text/image/location/link）
        String content = requestMap.get( "Content" );                                  // 消息内容
        String eventType = requestMap.get( "Event" );
        // 默认返回的文本消息内容
        String respContent = "请求处理异常，请稍候尝试！";
        // 事件KEY值，与创建自定义菜单时指定的KEY值对应
        String eventKey = requestMap.get( "EventKey" );
        logger.info( "eventKey: " + eventKey );
        if (eventKey.equals( "WKQD" )) {
        } else if (eventKey.equals( "WKXX" )) {
        } else if (eventKey.equals( "HSSD" )) {
        } else if (eventKey.equals( "LDSX" )) {
        }
        return respContent;
    }
}