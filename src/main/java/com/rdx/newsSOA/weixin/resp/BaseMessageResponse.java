package com.rdx.newsSOA.weixin.resp;

import lombok.Data;

/**
 * 消息基类（公众帐号 -> 普通用户）
 *
 * @author manson
 * @date 2016-01-26
 */
@Data
public class BaseMessageResponse {
	// 接收方帐号（收到的OpenID）
	private String ToUserName;
	// 开发者微信号
	private String FromUserName;
	// 消息创建时间 （整型）
	private long CreateTime;
	// 消息类型（text/music/news）
	private String MsgType;
	// 位0x0001被标志时，星标刚收到的消息
	private int FuncFlag;
}
