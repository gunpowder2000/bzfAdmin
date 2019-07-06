package com.hust.dao.job.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.job.JobDao;

import com.hust.model.job.Job;

public class JobDaoImpl extends BaseDao implements JobDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hust.dao.job.JobDao#getJob()
	 */
	public List<Job> getJob() {
		List<Job> result = new ArrayList<Job>();
		try {
			con = getConnection(1);
			String sql = "select * from tbl_job_info ";
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Job job = new Job(rs.getString("jobid"), rs.getString("jobname"));
				result.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
}
