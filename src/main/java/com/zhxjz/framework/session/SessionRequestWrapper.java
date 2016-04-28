package com.zhxjz.framework.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.BooleanUtils;

import com.zhxjz.common.CustomizedPropertyPlaceholderConfigurer;
import com.zhxjz.framework.session.page.PageParameterUtil;

public class SessionRequestWrapper extends HttpServletRequestWrapper {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected boolean hasSessionRequestWrapper;

	public SessionRequestWrapper(HttpServletRequest request, HttpServletResponse response) {
		super(request);
		this.response = response;
		boolean isEnableDistributedSession = BooleanUtils.toBoolean(CustomizedPropertyPlaceholderConfigurer.getContextProperty("session.isEnableDistributedSession"));
		if (isEnableDistributedSession) {
			this.request = new DisSessionRequestWrapper(request, response);
			hasSessionRequestWrapper = true;
		} else {
			this.request = request;
			hasSessionRequestWrapper = false;
		}
	}

	@Override
	public HttpSession getSession(boolean create) {
		if (hasSessionRequestWrapper) {
			return this.request.getSession(create);
		} else {
			return super.getSession(create);
		}
	}

	@Override
	public HttpSession getSession() {
		if (hasSessionRequestWrapper) {
			return this.request.getSession();
		} else {
			return super.getSession();
		}
	}

	@Override
	public String getParameter(String name) {
		String[] values = this.getParameterValues(name);
		if (values == null) {
			return null;
		} else {
			return values[0];
		}
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values;
		if (PageParameterUtil.isSpecialName(name)) {
			String value = PageParameterUtil.getParameterValues(request, response, name);
			values = new String[] { value };
		} else {
			// 当前不少特殊的参数名称，直接返回
			values = super.getParameterValues(name);
		}
		return values;
	}

	@Override
	public Object getAttribute(String name) {
		return super.getAttribute(name);
	}

}
