package com.zhxjz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.zhxjz.framework.util.common.SerializeUtil;
import com.zhxjz.model.constant.ConstantData;
import com.zhxjz.service.CacheService;

/**
 * Redis字节缓存实现
 * 
 * @author caozj
 */
@Service("redisByteService")
public class RedisByteCacheServiceImpl implements CacheService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private int redisPort = 6379;

	@Value("${redis.password}")
	private String redisPassword;

	private int timeout = ConstantData.REDIS_TOMEOUT;

	private JedisPool pool = null;

	@PostConstruct
	public void init() {
		if (StringUtils.isEmpty(redisHost)) {
			logger.info("redisHost没配置,不初始化redis客户端");
			return;
		}
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(200);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			config.setMaxWaitMillis(1000 * 30);
			// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
			config.setTestOnBorrow(true);
			if (StringUtils.isEmpty(redisPassword)) {
				pool = new JedisPool(config, redisHost, redisPort, timeout);
			} else {
				pool = new JedisPool(config, redisHost, redisPort, timeout, redisPassword);
			}
			logger.info("pool:" + pool);
		} catch (Exception e) {
			throw new RuntimeException("不能初始化Redis客户端", e);
		}
	}

	@Override
	public Object get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			byte[] data = jedis.get(key.getBytes());
			if (data == null || data.length <= 0) {
				return null;
			}
			return SerializeUtil.unserialize(data);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void set(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void set(String key, Object value, int second) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key.getBytes(), SerializeUtil.serialize(value));
			jedis.expire(key.getBytes(), second);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void hset(String key, String field, Object value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.hset(key.getBytes(), field.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public Object hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			byte[] data = jedis.hget(key.getBytes(), field.getBytes());
			if (data == null || data.length <= 0) {
				return null;
			}
			return SerializeUtil.unserialize(data);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void remove(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.del(key.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public long getAutoId(String key) {
		Jedis jedis = null;
		long id = 1;
		try {
			jedis = pool.getResource();
			id = jedis.incr(key.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
		return id;
	}

	@Override
	public void lpush(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.lpush(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public void rpush(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.rpush(key.getBytes(), SerializeUtil.serialize(value));
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
	}

	@Override
	public List<Object> lrange(String key) {
		Jedis jedis = null;
		List<Object> result = null;
		try {
			jedis = pool.getResource();
			List<byte[]> list = jedis.lrange(key.getBytes(), 0, -1);
			result = new ArrayList<Object>(list.size());
			for (byte[] o : list) {
				result.add(SerializeUtil.unserialize(o));
			}
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
		return result;
	}

	@Override
	public Object lpop(String key) {
		Jedis jedis = null;
		Object object = null;
		try {
			jedis = pool.getResource();
			byte[] bs = jedis.lpop(key.getBytes());
			object = SerializeUtil.unserialize(bs);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
		return object;
	}

	@Override
	public Object rpop(String key) {
		Jedis jedis = null;
		Object object = null;
		try {
			jedis = pool.getResource();
			byte[] bs = jedis.rpop(key.getBytes());
			object = SerializeUtil.unserialize(bs);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
		return object;
	}

	@Override
	public Object lindex(String key, int index) {
		Jedis jedis = null;
		Object object = null;
		try {
			jedis = pool.getResource();
			byte[] bs = jedis.lindex(key.getBytes(), index);
			object = SerializeUtil.unserialize(bs);
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			close(jedis);
		}
		return object;
	}

	private void close(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}
	}

	@Override
	public long llen(String key) {
		Jedis jedis = null;
		long object;
		try {
			jedis = pool.getResource();
			object = jedis.llen(key.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return object;
	}

	@Override
	public List<String> keys() {
		Jedis jedis = null;
		List<String> list = null;
		try {
			jedis = pool.getResource();
			Set<byte[]> set = jedis.keys("*".getBytes());
			list = new ArrayList<String>(set.size());
			for (byte[] bs : set) {
				list.add(SerializeUtil.unserialize(bs).toString());
			}
		} catch (Exception e) {
			throw new RuntimeException("Redis出现错误！", e);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return list;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

}
