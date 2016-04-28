package com.zhxjz.framework.session;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.zhxjz.common.CustomizedPropertyPlaceholderConfigurer;
import com.zhxjz.service.impl.RedisByteCacheServiceImpl;

@Component("sessionCache")
public class SessionCacheSessionImpl extends RedisByteCacheServiceImpl {

	@PostConstruct
	public void init() {
		setRedisHost(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.redis.host"));
		setRedisPort(NumberUtils.toInt(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.redis.port"), 6379));
		setRedisPassword(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.redis.password"));
		super.init();
	}

}
