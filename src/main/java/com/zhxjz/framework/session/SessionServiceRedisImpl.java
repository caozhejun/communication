package com.zhxjz.framework.session;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhxjz.framework.util.ApplicationContextUtil;
import com.zhxjz.service.CacheService;

/**
 * SessionService的redis实现
 * 
 * @author caozj
 *
 */
public class SessionServiceRedisImpl implements SessionService {

	protected static final Log logger = LogFactory.getLog(SessionServiceRedisImpl.class);

	private CacheService cacheService;

	private static final class SessionServiceHolder {
		private static final SessionServiceRedisImpl instance = new SessionServiceRedisImpl();
	}

	public static SessionServiceRedisImpl getInstance() {
		return SessionServiceHolder.instance;
	}

	private SessionServiceRedisImpl() {
		cacheService = (CacheService) ApplicationContextUtil.getBean("sessionCache");
	}

	/**
	 * 根据session id获取session key.
	 * 
	 * @param sid
	 * @return
	 */
	private String getSessionKey(String sid) {
		return "sid:" + sid;
	}

	/**
	 * 根据session id获取session对象.
	 * 
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getSession(String sid) {
		String key = this.getSessionKey(sid);
		return (Map<String, Object>) (cacheService.get(key));
	}

	/**
	 * 保存Session对象.
	 * 
	 * @param sid
	 *            id
	 * @param session
	 *            map对象
	 * @param seconds
	 *            保存时间
	 */
	public void saveSession(String sid, Map<String, Object> session, int seconds) {
		String key = this.getSessionKey(sid);
		cacheService.set(key, session, seconds);
	}

	/**
	 * 移除session对象.
	 * 
	 * @param sid
	 */
	public void removeSession(String sid) {
		String key = this.getSessionKey(sid);
		cacheService.remove(key);
	}

}
