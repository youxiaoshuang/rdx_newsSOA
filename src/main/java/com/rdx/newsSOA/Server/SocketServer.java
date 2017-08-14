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
    Logger logger = Logger.getLogger( SocketServer.class );

    public void runServer() throws InterruptedException {
        Configuration config = new Configuration();
        config.setHostname( "127.0.0.1" );
        config.setPort( 3000 );

        SocketIOServer server = new SocketIOServer( config );
        CharteventListener chatlistener = new CharteventListener();
        ConnectionListener connlistener = new ConnectionListener();
        chatlistener.setServer( server );
        server.addConnectListener( connlistener );
        // chatevent为事件名称
        server.addEventListener( "chatevent", ChatObject.class, chatlistener );
        // 绑定客户端事件
        // 启动服务
        server.start();
        logger.info( "socket.io服务已启动" );
        Thread.sleep( Integer.MAX_VALUE );
        server.stop();
    }

    public static void main(String[] args) throws InterruptedException {
        new SocketServer().runServer();
    }

}
