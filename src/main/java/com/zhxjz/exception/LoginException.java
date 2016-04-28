package com.zhxjz.exception;

/**
 * 未登陆异常类
 * 
 * @author caozj
 * 
 */
public class LoginException extends RuntimeException {

	private static final long serialVersionUID = -2320399934788651830L;

	public LoginException(String message) {
		super(message);
	}
}
