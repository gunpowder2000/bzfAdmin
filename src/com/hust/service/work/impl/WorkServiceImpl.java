package com.hust.service.work.impl;

import java.util.List;

import com.hust.dao.work.WorkDao;
import com.hust.dao.work.impl.WorkDaoImpl;
import com.hust.model.work.Work;
import com.hust.service.work.WorkService;

public class WorkServiceImpl implements WorkService {
	
	/* (non-Javadoc)
	 * @see com.hust.service.work.impl.WorkService#insertWork(com.hust.model.work.Work)
	 */
	public int insertWork(Work work){
		int result=0;
		WorkDao workDao=new WorkDaoImpl();
		result=workDao.insertWork(work);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.work.impl.WorkService#updateWork(com.hust.model.work.Work)
	 */
	public int updateWork(Work work){
		int result=0;
		WorkDao workDao=new WorkDaoImpl();
		result=workDao.updateWork(work);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.work.impl.WorkService#deleteWork(com.hust.model.work.Work)
	 */
	public int deleteWork(Work work){
		int result=0;
		WorkDao workDao=new WorkDaoImpl();
		result=workDao.deleteWork(work);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.work.impl.WorkService#queryWorks(java.lang.String, int, int)
	 */
	public List<Work> queryWorks(String keyword, int page, int rows){
		List<Work> result=null;
		WorkDao workDao=new WorkDaoImpl();
		result=workDao.queryWorks(keyword, page, rows);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.work.impl.WorkService#queryWorksCount(java.lang.String)
	 */
	public int queryWorksCount(String keyword){
		int result=0;
		WorkDao workDao=new WorkDaoImpl();
		result=workDao.queryWorksCount(keyword);
		return result;
	}

}
