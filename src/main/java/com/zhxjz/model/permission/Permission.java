package com.zhxjz.model.permission;

import java.io.Serializable;

/**
 * 权限
 * 
 * @author caozj
 * 
 */
public class Permission implements Serializable {

	private static final long serialVersionUID = 7482598198211538944L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 对应的url地址(多个url用英文逗号隔开)
	 */
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Permission [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
