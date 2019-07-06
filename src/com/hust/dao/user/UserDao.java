package com.hust.dao.user;

import java.util.List;

import com.hust.model.user.User;

public interface UserDao {

	public abstract User getUser(String userNumber);

	public abstract List<User> searchUser(String key);
	
	public int addUser(User user);

	int editUser(User user);

	int changePassword(User user);

	int cancelUser(Long userid);

}