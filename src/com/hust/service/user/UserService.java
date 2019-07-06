package com.hust.service.user;

import java.util.List;

import com.hust.model.user.User;

public interface UserService {

	public abstract User getUser(String userNumber);
	public List<User> searchUser(String key);
	int addUser(User user);
	int editUser(User user);
	int changePassword(User user);
	int cancelUser(Long userid);

}