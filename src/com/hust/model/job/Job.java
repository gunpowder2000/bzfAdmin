package com.hust.model.job;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hust.factory.ConnectionFactory;

public class Job {
	private String jobid; // 职务编号
	private String jobname; // 职务名称

	/**
	 * @param jobid
	 * @param jobname
	 */
	public Job(String jobid, String jobname) {
		this.jobid = jobid;
		this.jobname = jobname;
	}
	public Job(String jobid) {
		Connection con = null;
		ConnectionFactory cf = new ConnectionFactory();
		try {
			con = cf.getConnection(1);
			String sql = "select * from tbl_job_info where jobid=?  ";
			PreparedStatement pstm = con.prepareStatement(sql);	
			pstm.setString(1, jobid);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()){
				this.jobid=rs.getString("jobid");
				this.jobname=rs.getString("jobname");
			}
			rs.close();
			pstm.close();
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
 * 
 */
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getJobid() {
		return jobid;
	}

	public String getJobname() {
		return jobname;
	}

	public void setJobid(String jobid) {
		this.jobid = jobid;
	}

	public void setJobname(String jobname) {
		this.jobname = jobname;
	}

}
