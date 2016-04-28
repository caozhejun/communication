package com.zhxjz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 公告
 * 
 * @author caozj
 *
 */
public class Notice implements Serializable {

	private static final long serialVersionUID = 3339636047984372956L;

	private int id;

	private String title;

	private String content;

	private Date time;

	private String userAccount;

	private int orgId;

	private int type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notice [id=");
		builder.append(id);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", time=");
		builder.append(time);
		builder.append(", userAccount=");
		builder.append(userAccount);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}

}
