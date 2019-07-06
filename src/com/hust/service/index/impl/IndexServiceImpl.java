package com.hust.service.index.impl;

import java.util.List;

import com.hust.dao.index.IndexDao;
import com.hust.dao.index.impl.IndexDaoImpl;
import com.hust.model.index.Index;
import com.hust.service.index.IndexService;

public class IndexServiceImpl implements IndexService {
	/* (non-Javadoc)
	 * @see com.hust.service.index.impl.IndexService#insertIndex(com.hust.model.index.Index)
	 */
	public int insertIndex(Index index){
		int result=0;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.insertIndex(index);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.index.impl.IndexService#updateIndex(com.hust.model.index.Index)
	 */
	public int updateIndex(Index index){
		int result=0;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.updateIndex(index);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.index.impl.IndexService#deleteIndex(com.hust.model.index.Index)
	 */
	public int deleteIndex(Index index){
		int result=0;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.deleteIndex(index);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.index.impl.IndexService#queryIndex(java.lang.String, int, int)
	 */
	public List<Index> queryIndex(String keyword, int page, int rows){
		List<Index> result=null;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.queryIndex(keyword,page,rows);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.index.impl.IndexService#queryIndexCount(java.lang.String)
	 */
	public int queryIndexCount(String keyword){
		int result=0;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.queryIndexCount(keyword);
		return result;
	}
	
	public int updatePassword(int userId,String oldPassowrd,String newPassword){
		int result=0;
		IndexDao indexDao=new IndexDaoImpl();
		result=indexDao.updatePassword(userId,oldPassowrd,newPassword);
		return result;
		
	}

}
