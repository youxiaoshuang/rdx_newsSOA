package com.rdx.newsSOA.weixin.pull;

import lombok.Data;

/**
 * 文本消息
 */
@Data
public class PullTextMessage {
    private String touser;  //UserID列表（消息接收者，多个接收者用‘|’分隔）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送————"touser": "UserID1|UserID2|UserID3"
    private String toparty; //PartyID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"toparty": " PartyID1 | PartyID2 "
    private String totag;   //TagID列表，多个接受者用‘|’分隔。当touser为@all时忽略本参数————"totag": " TagID1 | TagID2 "
    private String msgtype; //text
    private String agentid; //企业应用的id，整型。可在应用的设置页面查看
    private Content text; //消息内容
    private String safe;
}
