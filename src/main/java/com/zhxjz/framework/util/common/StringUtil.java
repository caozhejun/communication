package com.zhxjz.framework.util.common;

/**
 * 字符串工具类
 * 
 * @author caozj
 *
 */
public class StringUtil {

	/**
	 * 首字母小写
	 * 
	 * @param content
	 * @return
	 */
	public static String getFirstLower(String content) {
		return content.substring(0, 1).toLowerCase() + content.substring(1);
	}

	/**
	 * 首字母大写
	 * 
	 * @param content
	 * @return
	 */
	public static String getFirstUpper(String content) {
		return content.substring(0, 1).toUpperCase() + content.substring(1);
	}

}
