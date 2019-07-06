package com.hust.model.rolemenu;

import com.hust.model.menu.Menu;
import com.hust.model.role.Role;

public class RoleMenu {
	private Role role;
	private Menu menu;
	private String ishave;
	/**
	 * 
	 */
	public RoleMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param role
	 * @param menu
	 * @param ishave
	 */
	public RoleMenu(Role role, Menu menu, String ishave) {
		this.role = role;
		this.menu = menu;
		this.ishave = ishave;
	}
	public Role getRole() {
		return role;
	}
	public Menu getMenu() {
		return menu;
	}
	public String getIshave() {
		return ishave;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public void setIshave(String ishave) {
		this.ishave = ishave;
	}
	
}
