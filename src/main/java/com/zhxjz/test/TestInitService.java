package com.zhxjz.test;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxjz.framework.util.redis.RedisServerUtil;
import com.zhxjz.service.CacheService;

/**
 * 测试使用
 * 
 * @author caozj
 *
 */
@Service
public class TestInitService {
	
	@Autowired
	private CacheService redisByteService;

	@PostConstruct
	private void init() throws IOException, URISyntaxException {
//		RedisServerUtil.start(6379);
//		redisByteService.set("mytest", "hello world!!!");
	}
}
