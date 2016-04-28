package com.zhxjz.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zhxjz.exception.ForbidException;
import com.zhxjz.exception.LoginException;
import com.zhxjz.framework.model.json.JsonResult;

/**
 * 异常解析器
 * 
 * @author caozj
 */
@Component
public class CommonExceptionResolver implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(CommonExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("发生异常", ex);
		ModelAndView model = new ModelAndView();
		if (isJsonException(request)) {
			model.setViewName("message");
			model.addObject("message", new JsonResult(ex.getMessage(), 400).toJson());
		} else if (ex instanceof LoginException) {
			model.addObject("top", true);
			model.setViewName("login");
		} else if (ex instanceof ForbidException) {
			model.setViewName("error/forbid");
		} else {
			model.setViewName("error/error");
			model.addObject("message", ex.getMessage());
		}
		return model;
	}

	/**
	 * 判断是否是json格式请求，如果是json格式请求，返回json格式错误信息
	 * 
	 * @param request
	 * @return
	 */
	private boolean isJsonException(HttpServletRequest request) {
		boolean isJson = false;
		if (request.getParameter("json") != null || request.getParameter("jsonp") != null
				|| (request.getHeader("Accept") != null && request.getHeader("Accept").indexOf("json") > -1)) {
			isJson = true;
		}
		return isJson;
	}
}
