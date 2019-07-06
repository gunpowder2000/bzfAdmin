package com.hust.service.news.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.news.NewsDao;
import com.hust.dao.news.impl.NewsDaoImpl;
import com.hust.model.news.News;
import com.hust.service.news.NewsService;

public class NewsServiceImpl implements NewsService {
	/* (non-Javadoc)
	 * @see com.hust.service.news.impl.NewsService#insertNews(com.hust.model.news.News)
	 */
	public int insertNews(News news){
		int result=0;
		NewsDao newsDao=new NewsDaoImpl();
		result=newsDao.insertNews(news);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.news.impl.NewsService#updateNews(com.hust.model.news.News)
	 */
	public int updateNews(News news){
		int result=0;
		NewsDao newsDao=new NewsDaoImpl();
		result=newsDao.updateNews(news);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.news.impl.NewsService#deleteNews(com.hust.model.news.News)
	 */
	public int deleteNews(News news){
		int result=0;
		NewsDao newsDao=new NewsDaoImpl();
		result=newsDao.deleteNews(news);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.news.impl.NewsService#queryNews(java.lang.String, int, int)
	 */
	public List<News> queryNews(String keyword, int page, int rows){
		List<News> result=new ArrayList<News>();
		NewsDao newsDao=new NewsDaoImpl();
		result=newsDao.queryNews(keyword, page, rows);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.news.impl.NewsService#queryWorksCount(java.lang.String)
	 */
	public int queryNewsCount(String keyword){
		int result=0;
		NewsDao newsDao=new NewsDaoImpl();
		result=newsDao.queryNewsCount(keyword);
		return result;
	}

}
