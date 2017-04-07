package com.rdx.newsSOA.weixin.req;

/**
 * 音频消息
 *
 * @author manson
 * @date 2016-01-26
 */
public class VoiceMessageRequest extends BaseMessageRequest {
	// 媒体ID
	private String MediaId;
	// 语音格式
	private String Format;

	public String getMediaId() {
		return MediaId;
	}
}
