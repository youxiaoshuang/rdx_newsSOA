package com.rdx.newsSOA.weixin.req;

/**
 * 地理位置消息
 *
 * @author manson
 * @date 2016-01-26
 */

public class LocationMessageRequest extends BaseMessageRequest {
	// 地理位置维度
	private String Location_X;
	// 地理位置经度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;

}