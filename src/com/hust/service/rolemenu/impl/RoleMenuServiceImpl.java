package com.hust.service.rolemenu.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.rolemenu.RoleMenuDao;
import com.hust.dao.rolemenu.impl.RoleMenuDaoImpl;
import com.hust.model.role.Role;
import com.hust.model.rolemenu.RoleMenu;
import com.hust.service.rolemenu.RoleMenuService;

public class RoleMenuServiceImpl implements RoleMenuService {
	
	/* (non-Javadoc)
	 * @see com.hust.service.rolemenu.impl.RoleMenuService#getRoleMenuByRoleId(java.lang.String)
	 */
	public List<RoleMenu> getRoleMenuByRoleId(String roleid){
		List<RoleMenu> result=new ArrayList<RoleMenu>();
		RoleMenuDao roleMenuDao=new RoleMenuDaoImpl();
		result=roleMenuDao.getRoleMenuByRoleId(roleid);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.rolemenu.impl.RoleMenuService#saveRoleMenu(int, java.lang.String)
	 */
	public int saveRoleMenu(int roleid, String menuIds){
		int result=0;
		RoleMenuDao roleMenuDao=new RoleMenuDaoImpl();
		result=roleMenuDao.saveRoleMenu(roleid, menuIds);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.rolemenu.impl.RoleMenuService#getMenuByLevelFromList(java.util.List, int)
	 */
	public List<RoleMenu> getMenuByLevelFromList(List<RoleMenu> sourcelist, int menulevel){
		List<RoleMenu> result=new ArrayList<RoleMenu>();
		RoleMenuDao roleMenuDao=new RoleMenuDaoImpl();
		result=roleMenuDao.getMenuByLevelFromList(sourcelist, menulevel);
		return result;
	}
	
	public List<Role> getRole(){
		List<Role> result=new ArrayList<Role>();
		RoleMenuDao roleMenuDao=new RoleMenuDaoImpl();
		result=roleMenuDao.getRole();
		return result;
	}

}
