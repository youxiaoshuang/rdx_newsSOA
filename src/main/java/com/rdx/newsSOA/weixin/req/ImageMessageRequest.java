package com.rdx.newsSOA.weixin.req;

import lombok.Data;

/**
 * 地理位置消息
 *
 * @author manson
 * @date 2016-01-26
 */
@Data
public class ImageMessageRequest extends BaseMessageRequest {
	// 图片链接
	private String picUrl;

	private String mediaId;

	private String imageKey;

}
