package com.hust.dao.rolemenu.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.rolemenu.RoleMenuDao;
import com.hust.model.menu.Menu;


import com.hust.model.role.Role;
import com.hust.model.rolemenu.RoleMenu;

public class RoleMenuDaoImpl extends BaseDao implements RoleMenuDao {
	/* (non-Javadoc)
	 * @see com.hust.dao.rolemenu.impl.RoleMenuDao#getRoleMenuByRoleId(java.lang.String)
	 */
	public List<RoleMenu> getRoleMenuByRoleId(String roleid) {
		List<RoleMenu> result = new ArrayList<RoleMenu>();
		try {
			con = getConnection(1);
			String sql = "select a.*,b.roleid,case isnull(convert(varchar,b.roleid),'null')when 'null' then '0'else '1' end ishave from tbl_menu a left join tbl_role_menu b on a.menuid=b.menuid and b.roleid=? where a.state='1' ";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, roleid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Role role = new Role();
				Menu menu = new Menu();
				RoleMenu roleMenu = new RoleMenu();
				menu.setMenuId(rs.getString("menuid"));
				menu.setMenuName(rs.getString("menuname"));
				menu.setUrl(rs.getString("url"));
				menu.setState(rs.getString("state"));
				menu.setMenuLevel(rs.getInt("menulevel"));
				menu.setIsLeafe(rs.getString("isleafe"));
				role.setRoleId(rs.getInt("roleid"));
				roleMenu.setMenu(menu);
				roleMenu.setRole(role);
				roleMenu.setIshave(rs.getString("ishave"));
				result.add(roleMenu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	public List<Role> getRole() {
		List<Role> result = new ArrayList<Role>();
		try {
			con = getConnection(1);
			String sql = "select * from tbl_role order by roleid";
			pstm = con.prepareStatement(sql);			
			rs = pstm.executeQuery();
			while (rs.next()) {
				Role role = new Role();				
				role.setRoleId(rs.getInt("roleid"));
				role.setRoleName(rs.getString("rolename"));			
				result.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.rolemenu.impl.RoleMenuDao#saveRoleMenu(int, java.lang.String)
	 */
	public int saveRoleMenu(int roleid, String menuIds) {
		int result = 0;
		int temp1 = 0;
		int temp2 = 0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_role_menu where roleid=? ";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, roleid);
			temp1 = pstm.executeUpdate();
			String[] menuidsz = menuIds.split(",");
			for (int i = 0; i < menuidsz.length; i++) {
				sql = "insert into tbl_role_menu(roleid,menuid) values(?,?)";
				pstm = con.prepareStatement(sql);
				pstm.setInt(1, roleid);
				pstm.setString(2, menuidsz[i]);
				temp2 = pstm.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		result=temp1+temp2;
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.rolemenu.impl.RoleMenuDao#getMenuByLevelFromList(java.util.List, int)
	 */
	public List<RoleMenu> getMenuByLevelFromList(List<RoleMenu> sourcelist, int menulevel) {
		List<RoleMenu> result = new ArrayList<RoleMenu>();
		for (RoleMenu menu : sourcelist) {
			if (menulevel == menu.getMenu().getMenuLevel()) {
				result.add(menu);
			}
		}
		return result;

	}

}
