package com.hust.model.role;

public class Role {
private int roleId; //角色序号
private String roleName; //角色名称
public Role(int roleId, String roleName) {
	this.roleId = roleId;
	this.roleName = roleName;
}
/**
 *@author 钢 
 */
public Role() {
	super();
}
public int getRoleId() {
	return roleId;
}
public String getRoleName() {
	return roleName;
}
public void setRoleId(int roleId) {
	this.roleId = roleId;
}
public void setRoleName(String roleName) {
	this.roleName = roleName;
}


}
