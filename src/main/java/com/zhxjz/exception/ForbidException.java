package com.zhxjz.exception;

/**
 * 拒绝访问异常类
 * 
 * @author caozj
 * 
 */
public class ForbidException extends RuntimeException {

	private static final long serialVersionUID = -2320399934788651830L;

	public ForbidException(String message) {
		super(message);
	}
}
