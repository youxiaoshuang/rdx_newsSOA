package com.rdx.newsSOA.listener;

import com.rdx.newsSOA.Server.SocketServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InstantiationTracingBeanPostProcessor implements
        ServletContextListener {
	private Logger logger = Logger.getLogger(InstantiationTracingBeanPostProcessor.class);
	@Autowired
	private SocketServer socketServer;
     /**
      * 初始化完运行
      */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		logger.info("tomcat启动后执行成功");

	}
    /**
     * 宕机后运行
     */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

}
