package com.zhxjz.framework.session.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 页面特殊参数.
 * 
 * @author caozj
 * 
 */
public interface PageParameter {

	/**
	 * 参数名称(区分大小写)，在spring初始化时使用.
	 * 
	 * @return
	 */
	String getKey();

	/**
	 * 获取参数值.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	String getValue(HttpServletRequest request, HttpServletResponse response);

}
