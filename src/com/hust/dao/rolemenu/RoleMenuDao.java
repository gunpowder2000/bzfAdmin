package com.hust.dao.rolemenu;

import java.util.List;


import com.hust.model.role.Role;
import com.hust.model.rolemenu.RoleMenu;

public interface RoleMenuDao {

	public abstract List<RoleMenu> getRoleMenuByRoleId(String roleid);

	public abstract int saveRoleMenu(int roleid, String menuIds);

	public abstract List<RoleMenu> getMenuByLevelFromList(List<RoleMenu> sourcelist, int menulevel);
	
	public abstract List<Role> getRole();

}