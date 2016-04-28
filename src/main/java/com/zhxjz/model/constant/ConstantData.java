package com.zhxjz.model.constant;

/**
 * 常量类
 * 
 * @author caozj
 * 
 */
public interface ConstantData {

	/**
	 * 树的根节点ID
	 */
	int TREE_ROOT_ID = -1;

	/**
	 * 默认的编码集
	 */
	String DEFAULT_CHARSET = "UTF-8";

	/**
	 * session过期时间
	 */
	int SESSION_TIMEOUT = 30 * 60 * 1000;

	/**
	 * redis连接超时时间
	 */
	int REDIS_TOMEOUT = 30 * 1000;

	/**
	 * session的cookie
	 */
	String SESSIONID_COOKIE_NAME = "SESSIONID";

	/**
	 * 超级管理员的机构id
	 */
	int ADMIN_ORGID = -9999;

}
