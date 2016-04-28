package com.zhxjz.common;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zhxjz.exception.LoginException;
import com.zhxjz.framework.session.SessionUtil;
import com.zhxjz.framework.util.common.PathUtil;

/**
 * 系统登陆拦截器
 * 
 * @author caozj
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Value("${login.interceptor.exclude}")
	private String excludeUrl;

	private List<String> excludeUrls = new ArrayList<String>();

	@PostConstruct
	private void init() {
		String[] urls = excludeUrl.split(",");
		for (String url : urls) {
			excludeUrls.add(url);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = SessionUtil.getRequestUri(request);
		for (String excludeUrl : excludeUrls) {
			if (PathUtil.match(requestUri, excludeUrl)) {
				return true;
			}
		}
		if (SessionUtil.isLogin(request.getSession())) {
			return true;
		}
		throw new LoginException("您还未登陆系统");
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
