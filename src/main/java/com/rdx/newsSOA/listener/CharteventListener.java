package com.rdx.newsSOA.listener;


import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.rdx.newsSOA.dto.ChatObject;
import com.rdx.newsSOA.util.JsonTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CharteventListener implements DataListener<ChatObject> {

	SocketIOServer server;

	Logger logger = LoggerFactory.getLogger( this.getClass() );

	public void setServer(SocketIOServer server) {
		this.server = server;
	}

	public void onData(SocketIOClient client, ChatObject data,
			AckRequest ackSender) throws Exception {
		// chatevent为 事件的名称， data为发送的内容
		System.out.printf( JsonTool.writeValueAsString( data ) );
		server.getBroadcastOperations().sendEvent( "chatevent",data );
	}
}