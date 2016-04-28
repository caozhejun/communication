package com.zhxjz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 朋友圈
 * 
 * @author caozj
 *
 */
public class Communication implements Serializable {

	private static final long serialVersionUID = 3339636047984372956L;

	private int id;

	private String title;

	private String content;

	private Date time;

	private String userAccount;

	private int orgId;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Communication [id=");
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
		builder.append(", userName=");
		builder.append(userName);
		builder.append("]");
		return builder.toString();
	}

}
