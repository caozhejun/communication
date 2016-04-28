package com.zhxjz.framework.session;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zhxjz.common.CustomizedPropertyPlaceholderConfigurer;
import com.zhxjz.model.constant.ConstantData;

public class HttpSessionWrapper implements HttpSession {

	protected static final Log logger = LogFactory.getLog(HttpSessionWrapper.class);

	private String sid;

	private Map<String, Object> map = null;

	private int expiry;

	private String FMT_CHARSET_KEY = "javax.servlet.jsp.jstl.fmt.request.charset";

	private long createTime;

	private long accessedTime;

	private String sessionType;

	public HttpSessionWrapper(String sid, int expiry) {
		this.sid = sid;
		this.expiry = expiry;
		createTime = System.currentTimeMillis();
		accessedTime = System.currentTimeMillis();
		sessionType = CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.distribute.type");
	}

	protected Map<String, Object> getMap() {
		if (this.map == null) {
			this.map = SessionServiceFactory.getInstance(sessionType).getSession(sid);
		}
		if (this.map == null) {
			this.map = new HashMap<String, Object>();
		}
		return this.map;
	}

	@Override
	public long getCreationTime() {
		return createTime;
	}

	@Override
	public String getId() {
		return this.sid;
	}

	@Override
	public long getLastAccessedTime() {
		return accessedTime;
	}

	@Override
	public ServletContext getServletContext() {
		return null;
	}

	@Override
	public void setMaxInactiveInterval(int interval) {

	}

	@Override
	public int getMaxInactiveInterval() {
		return 0;
	}

	@Override
	public HttpSessionContext getSessionContext() {
		return null;
	}

	@Override
	public Object getAttribute(String name) {
		accessedTime = System.currentTimeMillis();
		if (FMT_CHARSET_KEY.equals(name)) {
			return ConstantData.DEFAULT_CHARSET;
		}
		return this.getMap().get(name);
	}

	@Override
	public Object getValue(String name) {
		return this.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		accessedTime = System.currentTimeMillis();
		return (new Enumerator(this.getMap().keySet(), true));
	}

	@Override
	public String[] getValueNames() {
		accessedTime = System.currentTimeMillis();
		String[] names = new String[this.getMap().keySet().size()];
		int index = 0;
		for (String key : this.getMap().keySet()) {
			names[index++] = key;
		}
		return names;
	}

	@Override
	public void setAttribute(String key, Object value) {
		accessedTime = System.currentTimeMillis();
		if (FMT_CHARSET_KEY.equals(key)) {
			return;
		}
		this.getMap().put(key, value);
		SessionServiceFactory.getInstance(sessionType).saveSession(this.sid, this.getMap(), expiry);
	}

	@Override
	public void putValue(String name, Object value) {
		this.setAttribute(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		accessedTime = System.currentTimeMillis();
		this.getMap().remove(name);
		SessionServiceFactory.getInstance(sessionType).saveSession(this.sid, this.getMap(), expiry);
	}

	@Override
	public void removeValue(String name) {
		this.removeAttribute(name);
	}

	@Override
	public void invalidate() {
		accessedTime = System.currentTimeMillis();
		this.getMap().clear();
		SessionServiceFactory.getInstance(sessionType).removeSession(this.sid);
		logger.info("清空session:" + this.sid);
	}

	@Override
	public boolean isNew() {
		return false;
	}

}
