package com.rdx.newsSOA.weixin.resp;

import lombok.Data;

import java.util.List;

/**
 * 文本消息
 *
 * @author manson
 * @date 2016-01-26
 */
@Data
public class NewsMessageResponse extends BaseMessageResponse {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;
	// 多条图文消息信息，默认第一个item为大图
	private List<Article> Articles;

}