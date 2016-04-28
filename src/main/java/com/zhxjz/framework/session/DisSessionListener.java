package com.zhxjz.framework.session;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DisSessionListener implements HttpSessionListener {

	private static final Log logger = LogFactory.getLog(DisSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		logger.info("创建session:" + se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		se.getSession().invalidate();
		logger.info("清空session:" + se.getSession().getId());
	}

}
