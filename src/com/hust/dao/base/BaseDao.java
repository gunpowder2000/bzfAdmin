package com.hust.dao.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hust.factory.ConnectionFactory;

public class BaseDao {
	public Connection con = null;
	public PreparedStatement pstm = null;
	public ResultSet rs = null;

	public Connection getConnection(int i) {
		ConnectionFactory cf = new ConnectionFactory();
		Connection result = null;
		try {
			result = cf.getConnection(i);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void freeConnection() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
