package com.rdx.newsSOA.weixin.pull;

import lombok.Data;

/**
 *  微信官方返回的消息结果
 */
@Data
public class PullResponse {
    private String errcode;
    private String errmsg;
    private String invaliduser;
    private String invalidparty;
    private String invalidtag;
}
