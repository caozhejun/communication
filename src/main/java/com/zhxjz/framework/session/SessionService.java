package com.zhxjz.framework.session;

import java.util.Map;

public interface SessionService {

	/**
	 * 根据session id获取session对象.
	 * 
	 * @param sid
	 * @return
	 */
	public Map<String, Object> getSession(String sid);

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
	public void saveSession(String sid, Map<String, Object> session, int seconds);

	/**
	 * 移除session对象.
	 * 
	 * @param sid
	 */
	public void removeSession(String sid);

}
