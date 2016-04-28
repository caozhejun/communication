package com.zhxjz.controller.form;

public class UserSearchForm {

	/**
	 * 用户名
	 */
	private String name;

	private int orgId;

	private int status;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserSearchForm [name=");
		builder.append(name);
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
		builder.append("]");
		return builder.toString();
	}

}
