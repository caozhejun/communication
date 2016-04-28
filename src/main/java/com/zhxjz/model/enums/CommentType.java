package com.zhxjz.model.enums;

public enum CommentType {

	Communication(1, "朋友圈");

	private int id;

	private String name;

	private CommentType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
