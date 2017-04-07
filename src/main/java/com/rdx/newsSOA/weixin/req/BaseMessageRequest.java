package com.rdx.newsSOA.weixin.req;

import lombok.Data;

/**
 * 地理位置消息
 *
 * @author manson
 * @date 2016-01-26
 */
@Data
public class BaseMessageRequest {

    private long MsgId; // 消息id，64位整型
    private String ToUserName; // 开发者微信号
    private String FromUserName; // 发送方帐号（一个OpenID）
    private long CreateTime; // 消息创建时间 （整型）
    private String MsgType; // 消息类型（text/image/location/link）
    private long AgentID;   //企业应用的id，整型。可在应用的设置页面查看

}
