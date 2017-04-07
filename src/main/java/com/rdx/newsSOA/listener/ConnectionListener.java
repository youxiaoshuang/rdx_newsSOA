package com.rdx.newsSOA.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.rdx.newsSOA.dto.ChatObject;

public class ConnectionListener implements ConnectListener{
	public void onConnect(SocketIOClient client) {
		// TODO Auto-generated method stub
		ChatObject data = new ChatObject();
		System.out.println(client.getSessionId().toString()+"上线成功");
		data.setUserName(client.getSessionId().toString());
		data.setMessage("上线成功");
		client.sendEvent("chatevent", data);
		client.joinRoom("测试房间一");
	}
	}
