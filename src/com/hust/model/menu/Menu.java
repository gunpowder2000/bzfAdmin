package com.hust.model.menu;

public class Menu {
	private String menuId; // 菜单序号
	private String menuName; // 菜单名称
	private String url; // 链接地址
	private String state; // 状态
	private int menuLevel; // 菜单层级
	private String isLeafe; // 是否末级
	

	public Menu() {
		super();
	}

	public Menu(String menuId, String menuName, String url, String state,
			int menuLevel, String isLeafe) {
		super();
		this.menuId = menuId;
		this.menuName = menuName;
		this.url = url;
		this.state = state;
		this.menuLevel = menuLevel;
		this.isLeafe = isLeafe;
		
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getIsLeafe() {
		return isLeafe;
	}

	public void setIsLeafe(String isLeafe) {
		this.isLeafe = isLeafe;
	}

	
}
