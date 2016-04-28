package com.zhxjz.framework.util.redis;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import redis.embedded.RedisServer;

/**
 * Redis服务器工具类，用于操作嵌入式Redis(目前只支持linux版本)
 * 
 * @author caozj
 *
 */
public class RedisServerUtil {

	private static final Set<Integer> ports = new HashSet<Integer>();

	private static final Map<Integer, RedisServer> serverMap = new HashMap<Integer, RedisServer>();

	/**
	 * 启动redis服务
	 * 
	 * @param port
	 *            端口号
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static void start(int port) throws IOException, URISyntaxException {
		if (!ports.contains(port)) {
			RedisServer server = new RedisServer(port);
			ports.add(port);
			serverMap.put(port, server);
			server.start();
		}
	}

	/**
	 * 停止redis服务
	 * 
	 * @param port
	 *            端口号
	 * @throws InterruptedException
	 */
	public static void stop(int port) throws InterruptedException {
		RedisServer server = serverMap.get(port);
		if (server != null) {
			server.stop();
			ports.remove(port);
			serverMap.remove(port);
		}
	}

	/**
	 * 停止所有的redis服务
	 * 
	 * @throws InterruptedException
	 */
	public static void stopAll() throws InterruptedException {
		for (Map.Entry<Integer, RedisServer> entry : serverMap.entrySet()) {
			entry.getValue().stop();
		}
		ports.clear();
		serverMap.clear();
	}

}
