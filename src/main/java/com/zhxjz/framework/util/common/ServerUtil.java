package com.zhxjz.framework.util.common;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

/**
 * 服务器工具类
 * 
 * @author caozj
 * 
 */
public class ServerUtil {

	/**
	 * 获取服务部署的根路径
	 * 
	 * @return
	 */
	public static String getWebRoot() {
		String path = ServerUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (path.indexOf("WEB-INF") > -1) {
			path = path.substring(0, path.indexOf("/WEB-INF"));
		} else {
			path = path.substring(0, path.indexOf("/target")) + "/src/main/webapp";
		}
		return path;
	}

	/**
	 * 获取代理服务器IP. .
	 * 
	 * @param request
	 * @return
	 */
	public static String getProxyIp(HttpServletRequest request) {
		String proxyIp = request.getHeader("X-Real-IP");
		if (proxyIp == null) {
			proxyIp = request.getHeader("RealIP");
		}
		if (proxyIp == null) {
			proxyIp = request.getRemoteAddr();
		}
		return proxyIp;
	}

	/**
	 * 获取代码src目录
	 * 
	 * @return
	 */
	public static File getSrcDir() {
		String webRoot = getWebRoot();
		File src = null;
		String mavenFile = new File(webRoot).getParent() + "/java";
		if (new File(mavenFile).exists()) {
			src = new File(mavenFile);
		} else {
			String webFile = new File(webRoot).getParent() + "/src";
			src = new File(webFile);
		}
		return src;
	}

	public static void main(String[] args) {
		System.out.println(getWebRoot());
		System.out.println(getSrcDir().getAbsolutePath());
	}
}
