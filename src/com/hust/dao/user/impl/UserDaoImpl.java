package com.hust.dao.user.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.user.UserDao;

import com.hust.model.job.Job;
import com.hust.model.user.User;

public class UserDaoImpl extends BaseDao implements UserDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.user.UserDao#getUser(java.lang.String)
	 */
	public User getUser(String userNumber) {
		User user = null;
		try {
			con = getConnection(1);
			String sql = "select a.*,b.roleid,c.rolename from tbl_user a left join tbl_user_role b on a.userid=b.userid left join tbl_role c on b.roleid=c.roleid where usernumber=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userNumber);
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsernumber(rs.getString("usernumber"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setMemo(rs.getString("memo"));
				user.setState(rs.getString("state"));
				user.setPassword(rs.getString("password"));				
				user.setRoleid(rs.getInt("roleid"));
				user.setRolename(rs.getString("rolename"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.user.UserDao#searchUser(java.lang.String)
	 */
	public List<User> searchUser(String key) {
		List<User> result = new ArrayList<User>();
		User user = null;
		try {
			con = getConnection(1);
			String sql = "select a.*,b.roleid,c.rolename from tbl_user a left join tbl_user_role b on a.userid=b.userid left join tbl_role c on b.roleid=c.roleid where usernumber like ? or username like ?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%" + key + "%");
			pstm.setString(2, "%" + key + "%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setUserid(rs.getInt("userid"));
				user.setUsernumber(rs.getString("usernumber"));
				user.setUsername(rs.getString("username"));
				user.setPhone(rs.getString("phone"));
				user.setMemo(rs.getString("memo"));
				user.setState(rs.getString("state"));
				user.setPassword(rs.getString("password"));				
				user.setRoleid(rs.getInt("roleid"));
				user.setRolename(rs.getString("rolename"));
				result.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}
		return result;
	}
	
	public int addUser(User user){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_user(usernumber,username,phone,memo,password) values(?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, user.getUsernumber());
			pstm.setString(2, user.getUsername());
			pstm.setString(3, user.getPhone());
			pstm.setString(4, user.getMemo());
			pstm.setString(5, user.getPassword());			
			result=pstm.executeUpdate();
			sql = "insert into tbl_user_role(roleid,userid) values(?,(select userid from tbl_user where usernumber=?))";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, user.getRoleid());
			pstm.setString(2, user.getUsernumber());			
			result=pstm.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}
		
		return result;
	}
	
	public int editUser(User user){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_user set username=?,phone=?,memo=? where userid=?";
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, user.getUsername());
			pstm.setString(2, user.getPhone());
			pstm.setString(3, user.getMemo());			
			pstm.setLong(5, user.getUserid());
			result=pstm.executeUpdate();
			sql = "delete tbl_user_role where userid=?";
			pstm = con.prepareStatement(sql);		
			pstm.setInt(1, user.getUserid());			
			result=pstm.executeUpdate();
			sql = "insert into tbl_user_role(roleid,userid) values(?,?)";
			pstm = con.prepareStatement(sql);			
			pstm.setInt(1, user.getRoleid());
			pstm.setInt(2, user.getUserid());			
			result=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}		
		return result;
	}
	
	public int changePassword(User user){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_user set password=? where userid=?";
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, user.getPassword());
			pstm.setLong(2, user.getUserid());			
			result=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}		
		return result;
	}
	
	public int cancelUser(Long userid){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_user set state=? where userid=?";
			pstm = con.prepareStatement(sql);			
			pstm.setString(1, "0");
			pstm.setLong(2, userid);			
			result=pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			freeConnection();
		}		
		return result;
	}
	
	
}
