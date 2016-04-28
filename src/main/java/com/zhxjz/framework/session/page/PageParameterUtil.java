package com.zhxjz.framework.session.page;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageParameterUtil {

	private static final Map<String, PageParameter> data = new HashMap<String, PageParameter>();

	public static void registerPageParameter(PageParameter page) {
		data.put(page.getKey(), page);
	}

	public static boolean isSpecialName(String name) {
		return data.containsKey(name);
	}

	public static String getParameterValues(HttpServletRequest request, HttpServletResponse response, String name) {// NOPMD
		PageParameter page = data.get(name);
		if (page == null) {
			throw new IllegalArgumentException("未知参数名称[" + name + "].");
		}
		return page.getValue(request, response);
	}
}
