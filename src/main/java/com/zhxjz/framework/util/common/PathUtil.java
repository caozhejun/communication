package com.zhxjz.framework.util.common;

import org.springframework.util.AntPathMatcher;

/**
 * url路径工具类
 * 
 * @author caozj
 * 
 */
public class PathUtil {

	private static AntPathMatcher match = new AntPathMatcher();

	/**
	 * 判断目标url和ant的url路径是否匹配
	 * 
	 * @param targetUrl
	 *            目标url
	 * @param antPath
	 *            ant路径
	 * @return
	 */
	public static boolean match(String targetUrl, String antPath) {
		return match.match(antPath, targetUrl);
	}

	public static void main(String[] args) {
		System.out.println(PathUtil.match("/a/b.do", "/a/**"));
		System.out.println(PathUtil.match("/a/b.do", "/a/*.do"));
		System.out.println(PathUtil.match("/a/b.do", "/b/*.do"));
		System.out.println(PathUtil.match("/a/b.do", "/b/**"));
		System.out.println(PathUtil.match("/a/b.do", "/a/c.do"));
		System.out.println(PathUtil.match("/a/b.do", "/a/b.do"));
	}

}
