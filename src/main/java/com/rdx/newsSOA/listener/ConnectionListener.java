package com.rdx.newsSOA.listener;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.rdx.newsSOA.dto.ChatObject;
import com.rdx.newsSOA.util.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConnectionListener implements ConnectListener,DataListener<ChatObject> {
	SocketIOServer server;
	Logger logger = LoggerFactory.getLogger( this.getClass() );

	public void setServer(SocketIOServer server) {
		this.server = server;
	}
	public void onConnect(SocketIOClient client) {
		// TODO Auto-generated method stub
		ChatObject data = new ChatObject();
		System.out.println(client.getSessionId().toString()+"上线成功");
		Object o = CacheUtil.get( client.getSessionId().toString() );
		if (o != null) {
			data.setUsername(o.toString());
			data.setContent(o.toString()+"上线");
			server.getBroadcastOperations().sendEvent("login", data);
		}
	}

	@Override
	public void onData(SocketIOClient client, ChatObject data, AckRequest ackSender) throws Exception {

	}
}
