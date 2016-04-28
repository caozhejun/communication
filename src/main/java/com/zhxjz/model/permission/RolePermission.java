package com.zhxjz.model.permission;

/**
 * 角色，权限 关系对象
 * 
 * @author caozj
 * 
 */
public class RolePermission {

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 权限名称
	 */
	private String permissionName;

	public RolePermission() {
	}

	public RolePermission(String roleName, String permissionName) {
		this.roleName = roleName;
		this.permissionName = permissionName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RolePermission [roleName=");
		builder.append(roleName);
		builder.append(", permissionName=");
		builder.append(permissionName);
		builder.append("]");
		return builder.toString();
	}

}
