package com.hust.dao.index;

import java.util.List;

import com.hust.model.index.Index;

public interface IndexDao {

	public abstract int insertIndex(Index index);

	public abstract int updateIndex(Index index);

	public abstract int deleteIndex(Index index);

	public abstract List<Index> queryIndex(String keyword, int page, int rows);

	public abstract int queryIndexCount(String keyword);
	
	public abstract int updatePassword(int userId,String oldPassowrd,String newPassword);

}