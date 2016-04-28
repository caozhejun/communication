package com.zhxjz.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 参与活动
 * 
 * @author caozj
 *
 */
public class Participation implements Serializable {

	private static final long serialVersionUID = 2741918180029810565L;

	private int id;

	private int activeId;

	private String userAccount;

	private Date time;

	private int num;

	private String remark;

	// 下面的属性都是页面显示需要，不保存到数据库
	/**
	 * 用户名
	 */
	private String name;

	private String email;

	private String telNo;

	private String floorNo;

	private String unitNo;

	private String roomNo;

	private String idNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActiveId() {
		return activeId;
	}

	public void setActiveId(int activeId) {
		this.activeId = activeId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Participation [id=");
		builder.append(id);
		builder.append(", activeId=");
		builder.append(activeId);
		builder.append(", userAccount=");
		builder.append(userAccount);
		builder.append(", time=");
		builder.append(time);
		builder.append(", num=");
		builder.append(num);
		builder.append(", remark=");
		builder.append(remark);
		builder.append("]");
		return builder.toString();
	}
}
