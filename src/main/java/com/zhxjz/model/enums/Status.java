package com.zhxjz.model.enums;

/**
 * 状态
 * 
 * @author caozj
 *
 */
public enum Status {

	DOING(1, "评审中"),

	APPROVE(2, "同意"),

	REFUSE(3, "拒绝");

	private Status(int id, String name) {
		this.id = id;
		this.name = name;
	}

	private int id;

	private String name;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
