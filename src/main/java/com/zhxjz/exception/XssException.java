package com.zhxjz.exception;

/**
 * XSS攻击检查异常类
 * 
 * @author caozj
 * 
 */
public class XssException extends RuntimeException {

	private static final long serialVersionUID = -2320399934788651830L;

	public XssException(String message) {
		super(message);
	}
}
