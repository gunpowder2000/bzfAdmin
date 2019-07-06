package com.hust.dao.news;

import java.util.List;

import com.hust.model.news.News;

public interface NewsDao {

	public abstract int insertNews(News news);

	public abstract int updateNews(News news);

	public abstract int deleteNews(News news);

	public abstract List<News> queryNews(String keyword, int page, int rows);

	public abstract int queryNewsCount(String keyword);

}