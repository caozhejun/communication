package com.zhxjz.model.enums;

/**
 * 公告类型
 * 
 * @author caozj
 *
 */
public enum NoticeType {

	COMMITTEE(1, "业主委员会"),

	ESTATE(2, "物业");

	private NoticeType(int id, String name) {
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
