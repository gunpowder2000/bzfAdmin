package com.hust.service.work;

import java.util.List;

import com.hust.model.work.Work;

public interface WorkService {

	public abstract int insertWork(Work work);

	public abstract int updateWork(Work work);

	public abstract int deleteWork(Work work);

	public abstract List<Work> queryWorks(String keyword, int page, int rows);

	public abstract int queryWorksCount(String keyword);

}