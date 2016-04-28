package com.zhxjz.framework.session;

/**
 * SessionService工厂类
 * 
 * @author caozj
 *
 */
public class SessionServiceFactory {

	public static SessionService getInstance(String type) {
		SessionService service = null;
		if ("redis".equalsIgnoreCase(type)) {
			service = SessionServiceRedisImpl.getInstance();
		} else if ("zookeeper".equalsIgnoreCase(type)) {
			service = SessionServiceZookeeperImpl.getInstance();
		}
		return service;
	}
}
