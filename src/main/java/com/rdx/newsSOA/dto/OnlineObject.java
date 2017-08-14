package com.rdx.newsSOA.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by youxiaoshuang on 2017/5/31.
 * Projiect rdx_newsSOA
 * Author youxiaoshuang
 */
@Data
public class OnlineObject {
    private List<String> onlineUsers;
    private Integer onlineCount;
    private ChatObject user;
}
