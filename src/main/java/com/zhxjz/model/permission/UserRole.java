package com.zhxjz.model.permission;

/**
 * 用户，角色 关系对象
 * 
 * @author caozj
 * 
 */
public class UserRole {

	/**
	 * 用户账号
	 */
	private String userAccount;

	/**
	 * 角色名称
	 */
	private String roleName;

	public UserRole() {

	}

	public UserRole(String userAccount, String roleName) {
		this.userAccount = userAccount;
		this.roleName = roleName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRole [userAccount=");
		builder.append(userAccount);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append("]");
		return builder.toString();
	}

}
