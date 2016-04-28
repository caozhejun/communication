package com.zhxjz.model.permission;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * 
 * @author caozj
 * 
 */
public class User implements Serializable {

	private static final long serialVersionUID = 2385355894980010312L;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 密码
	 */
	private String pwd;

	private int orgId;

	private int status;

	private String email;

	private String telNo;

	private String floorNo;

	private String unitNo;

	private String roomNo;

	private String idNo;

	private Date createTime;

	private String reason;

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}

	public String getUnitNo() {
		return unitNo;
	}

	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [account=");
		builder.append(account);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pwd=");
		builder.append(pwd);
		builder.append(", orgId=");
		builder.append(orgId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", email=");
		builder.append(email);
		builder.append(", telNo=");
		builder.append(telNo);
		builder.append(", floorNo=");
		builder.append(floorNo);
		builder.append(", unitNo=");
		builder.append(unitNo);
		builder.append(", roomNo=");
		builder.append(roomNo);
		builder.append(", idNo=");
		builder.append(idNo);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append(", reason=");
		builder.append(reason);
		builder.append("]");
		return builder.toString();
	}

}
