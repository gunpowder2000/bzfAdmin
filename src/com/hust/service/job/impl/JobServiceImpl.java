package com.hust.service.job.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.job.JobDao;
import com.hust.dao.job.impl.JobDaoImpl;
import com.hust.model.job.Job;
import com.hust.service.job.JobService;

public class JobServiceImpl implements JobService {
	/* (non-Javadoc)
	 * @see com.hust.service.job.impl.JobService#getJob()
	 */
	public List<Job> getJob(){
		List<Job> result=new ArrayList<Job>();
		JobDao jobDao=new JobDaoImpl();
		result=jobDao.getJob();
		return result;
	}

}
