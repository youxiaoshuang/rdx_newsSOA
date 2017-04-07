package com.rdx.newsSOA.weixin.resp;

import lombok.Data;

/**
 * 文本消息
 *
 * @author liufeng
 * @date 2013-05-19
 */
@Data
public class TextMessageResponse extends BaseMessageResponse {
	// 回复的消息内容
	private String Content;
}