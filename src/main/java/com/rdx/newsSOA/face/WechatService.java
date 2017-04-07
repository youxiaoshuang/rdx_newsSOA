package com.rdx.newsSOA.face;


import com.rdx.newsSOA.weixin.req.BaseMessageRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by youxiaoshuang on 2016/10/17.
 * Projiect fightting
 * Author youxiaoshuang
 */
public interface WechatService {

    String processRequest(HttpServletRequest request);

    /**
     * 事件点击
     * @param requestMap
     * @param baseMessageRequest
     * @return
     * @throws Exception
     */
    String click(Map<String, String> requestMap, BaseMessageRequest baseMessageRequest) throws Exception;
}
