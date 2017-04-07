package com.rdx.newsSOA.Processor;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * 防止连接池超时
 */

/**
 * 
 * @author youxiaoshuang
 * 
 */
public class DataSourceTask implements ApplicationListener<ContextRefreshedEvent> {
	private Logger logger = Logger.getLogger(DataSourceTask.class);

	/**
	 * 项目启动后执行
	 */
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		logger.info("测试");
		if (event.getApplicationContext().getParent() == null) {
			timeTask();
		}

	}

	private void timeTask() {
		Runnable runable = new Runnable() {
			public void run() {
				taskService();
			}
		};
		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
		service.scheduleAtFixedRate(runable, 10, 10, TimeUnit.SECONDS);// 十分钟刷一次
	}

	private void taskService() {
		// 与数据库保持连接
		logger.info("调用成功");
	}


}
