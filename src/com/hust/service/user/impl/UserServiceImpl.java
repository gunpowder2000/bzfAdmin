package com.hust.service.user.impl;



import java.util.ArrayList;
import java.util.List;

import com.hust.dao.user.UserDao;
import com.hust.dao.user.impl.UserDaoImpl;
import com.hust.model.user.User;
import com.hust.service.user.UserService;

public class UserServiceImpl implements UserService {
	/* (non-Javadoc)
	 * @see com.hust.service.user.impl.UserService#getUser(java.lang.String)
	 */
	public User getUser(String userNumber){
		User result=new User();
		UserDao userDao=new UserDaoImpl();
		result=userDao.getUser(userNumber);
		return result;
	}
	
	public List<User> searchUser(String key){
		List<User> result=new ArrayList<User>();
		UserDao userDao=new UserDaoImpl();
		result=userDao.searchUser(key);
		return result;
	}
	
	public int addUser(User user){
		int result=0;
		UserDao userDao=new UserDaoImpl();
		result=userDao.addUser(user);
		return result;				
	}
	
	public int editUser(User user){
		int result=0;
		UserDao userDao=new UserDaoImpl();
		result=userDao.editUser(user);
		return result;
	}
	
	public int changePassword(User user){
		int result=0;
		UserDao userDao=new UserDaoImpl();
		result=userDao.changePassword(user);
		return result;
	}
	
	public int cancelUser(Long userid){
		int result=0;
		UserDao userDao=new UserDaoImpl();
		result=userDao.cancelUser(userid);
		return result;
	}
	

}
