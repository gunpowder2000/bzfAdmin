package com.hust.dao.menu;

import java.util.List;

import com.hust.model.menu.Menu;

public interface MenuDao {

	public abstract List<Menu> getAllMenu();

	public abstract List<Menu> getMenuByUser(int userId);

	public abstract List<Menu> getMenuByLevelFromList(List<Menu> sourcelist, int menulevel);

}