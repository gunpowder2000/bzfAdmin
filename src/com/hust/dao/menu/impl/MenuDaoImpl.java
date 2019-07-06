package com.hust.dao.menu.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.menu.MenuDao;

import com.hust.model.menu.Menu;

public class MenuDaoImpl extends BaseDao implements MenuDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.menu.MenuDao#getAllMenu()
	 */
	public List<Menu> getAllMenu() {
		List<Menu> result = new ArrayList<Menu>();
		Menu menu = null;
		try {
			con = getConnection(1);
			String sql = "select * from tbl_menu order by menuid";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				menu = new Menu();
				menu.setMenuId(rs.getString("menuid"));
				menu.setMenuName(rs.getString("menuname"));
				menu.setUrl(rs.getString("url"));
				menu.setState(rs.getString("state"));
				menu.setMenuLevel(rs.getInt("menulevel"));
				menu.setIsLeafe(rs.getString("isleafe"));
				result.add(menu);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.menu.MenuDao#getMenuByUser(int)
	 */
	public List<Menu> getMenuByUser(int userId) {
		List<Menu> result = new ArrayList<Menu>();
		Menu menu = null;
		try {
			con = getConnection(1);
			String sql = " select * from tbl_menu a where menuid in ( "
            +" select distinct menuid from tbl_role_menu where roleid in ( select roleid from tbl_user_role where userid=? ) and len(menuid)=8 "
            +" union "
            +" select distinct substring(menuid,1,5) from tbl_role_menu where roleid in ( select roleid from tbl_user_role where userid=? ) and len(menuid)=8 "
            +" union "
            +" select distinct substring(menuid,1,2) from tbl_role_menu where roleid in ( select roleid from tbl_user_role where userid=? ) and len(menuid)=8 "
            +" ) and a.state=? order by a.menuid ";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, userId);
			pstm.setInt(2, userId);
			pstm.setInt(3, userId);
			pstm.setString(4, "1");
			rs = pstm.executeQuery();
			while (rs.next()) {
				menu = new Menu();
				menu.setMenuId(rs.getString("menuid"));
				menu.setMenuName(rs.getString("menuname"));
				menu.setUrl(rs.getString("url"));
				menu.setState(rs.getString("state"));
				menu.setMenuLevel(rs.getInt("menulevel"));
				menu.setIsLeafe(rs.getString("isleafe"));
				result.add(menu);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}
		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.menu.MenuDao#getMenuByLevelFromList(java.util.List,
	 * int)
	 */
	public List<Menu> getMenuByLevelFromList(List<Menu> sourcelist, int menulevel) {
		List<Menu> result = new ArrayList<Menu>();
		for (Menu menu : sourcelist) {
			if (menulevel == menu.getMenuLevel()) {
				result.add(menu);
			}
		}
		return result;

	}

}
