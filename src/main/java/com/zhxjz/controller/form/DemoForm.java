package com.zhxjz.controller.form;

public class DemoForm {

	private String demoMessage;

	private int type;

	private String demo;

	public String getDemoMessage() {
		return demoMessage;
	}

	public void setDemoMessage(String demoMessage) {
		this.demoMessage = demoMessage;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DemoForm [demoMessage=");
		builder.append(demoMessage);
		builder.append(", type=");
		builder.append(type);
		builder.append(", demo=");
		builder.append(demo);
		builder.append("]");
		return builder.toString();
	}

}
