package com.zhxjz.controller.form;

/**
 * Groovy控制台输入参数对象
 * 
 * @author caozj
 * 
 */
public class GroovyExecuteForm {

	private String userName;

	private String password;

	private String script;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GroovyExecuteForm [userName=");
		builder.append(userName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", script=");
		builder.append(script);
		builder.append("]");
		return builder.toString();
	}

}
