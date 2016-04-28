package com.zhxjz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 * 
 * @author caozj
 *
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = -4448189675763682723L;

	private int id;

	private String content;

	private Date time;

	private String userAccount;

	private String name;

	private int type;

	private int masterId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Comment [id=");
		builder.append(id);
		builder.append(", content=");
		builder.append(content);
		builder.append(", time=");
		builder.append(time);
		builder.append(", userAccount=");
		builder.append(userAccount);
		builder.append(", name=");
		builder.append(name);
		builder.append(", type=");
		builder.append(type);
		builder.append(", masterId=");
		builder.append(masterId);
		builder.append("]");
		return builder.toString();
	}

}
