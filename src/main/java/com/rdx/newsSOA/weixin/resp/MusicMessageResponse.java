package com.rdx.newsSOA.weixin.resp;

import lombok.Data;

/**
 * 音乐消息
 * 
 * @author manson
 * @date 2013-05-19
 */
@Data
public class MusicMessageResponse extends BaseMessageResponse {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}