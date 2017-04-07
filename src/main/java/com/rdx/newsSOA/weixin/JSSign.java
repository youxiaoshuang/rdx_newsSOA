package com.rdx.newsSOA.weixin;

import lombok.Data;

import java.io.Serializable;

@Data
public class JSSign implements Serializable {

	// 必填，企业号的唯一标识，此处填写企业号corpid
	private String appid;
	// 必填，生成签名的随机串
	private String nonce_str;
	// 必填，生成签名的时间戳
	private String timestamp;
	// 必填，签名，见附录1
	private String signature;
}