package com.rdx.newsSOA.Server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.rdx.newsSOA.dto.ChatObject;
import com.rdx.newsSOA.listener.CharteventListener;
import com.rdx.newsSOA.listener.ConnectionListener;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SocketServer {
	Logger logger = Logger.getLogger(SocketServer.class);

	public void runServer() {
		Configuration config = new Configuration();
		config.setHostname("127.0.0.1");
		config.setPort(8088);

		SocketIOServer server = new SocketIOServer(config);
		CharteventListener chatlistener = new CharteventListener();
		ConnectionListener connlistener = new ConnectionListener();
		chatlistener.setServer(server);
		server.addConnectListener(connlistener);
		// chatevent为事件名称
		server.addEventListener("chatevent", ChatObject.class, chatlistener);
		// 绑定客户端事件
		// 启动服务
		server.start();
		logger.info("socket.io服务已启动");
		try {
			Thread.sleep(Integer.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.stop();
	}

	public static void main(String[] args) {
		new SocketServer().runServer();
	}

}
