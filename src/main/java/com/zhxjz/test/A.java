package com.zhxjz.test;

import java.io.Serializable;
import java.util.Date;

public class A implements Serializable {

	private static final long serialVersionUID = -4614132884171091384L;

	private int id;

	private String name;

	private Date birthday;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("A [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", birthday=");
		builder.append(birthday);
		builder.append("]");
		return builder.toString();
	}

}
