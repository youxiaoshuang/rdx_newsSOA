package com.rdx.newsSOA.weixin.req;

/**
 * 地理位置消息
 *
 * @author manson
 * @date 2016-01-26
 */

public class LinkMessageRequest extends BaseMessageRequest {
	// 消息标题
	private String Title;
	// 消息描述
	private String Description;
	// 消息链接
	private String Url;
}
