package com.zhxjz.controller.uEditor;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.util.UrlPathHelper;

/**
 * 自定义的文件上传处理类，主要是为了过滤部分url可以不走Spring的文件上传，自己自定义文件上传处理
 * 
 * @author caozj
 * @date 2013-04-11
 * 
 */
public class CustomMultipartResolver extends CommonsMultipartResolver {

	/** 设置每个request的LOOKUP_PATH到attribute中,节省计算时间 */
	private static String LOOKUP_PATH_ATTRIBUTE_NAME = "__admin__lookup__path__";

	/** 被排除处理的URL列表 */
	private List<String> excludeUrlPatternList = Collections.emptyList();

	/** 路径匹配 */
	private PathMatcher pathMatcher = new AntPathMatcher();

	/** build urlpath */
	private UrlPathHelper urlPathHelper = new UrlPathHelper();

	@Override
	public boolean isMultipart(HttpServletRequest request) {
		if (excludeUrlPatternList.size() > 0) {
			String lookupPath = getLookupPathForRequest(request);
			for (String pattern : excludeUrlPatternList) {
				if (pattern.equals(lookupPath) || pathMatcher.match(pattern, lookupPath)) {
					return false;
				}
			}
		}

		return super.isMultipart(request);
	}

	/**
	 * 获取LookupPath
	 * 
	 * @param request
	 * @return
	 */
	private String getLookupPathForRequest(HttpServletRequest request) {
		String lockupPath = (String) request.getAttribute(LOOKUP_PATH_ATTRIBUTE_NAME);
		if (StringUtils.isEmpty(lockupPath)) {
			lockupPath = urlPathHelper.getLookupPathForRequest(request);
			request.setAttribute(LOOKUP_PATH_ATTRIBUTE_NAME, lockupPath);
		}
		return lockupPath;
	}

	public List<String> getExcludeUrlPatternList() {
		return excludeUrlPatternList;
	}

	public void setExcludeUrlPatternList(List<String> excludeUrlPatternList) {
		this.excludeUrlPatternList = excludeUrlPatternList;
	}
}
