package com.hust.dao.index.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.index.IndexDao;
import com.hust.model.index.Index;

public class IndexDaoImpl extends BaseDao implements IndexDao {
	/* (non-Javadoc)
	 * @see com.hust.dao.index.impl.IndexDao#insertIndex(com.hust.model.index.Index)
	 */
	public int insertIndex(Index index){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_index(index_title,index_image,index_image_url)values(?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, index.getIndexTitle());
			pstm.setString(2, index.getIndexImage());
			pstm.setString(3, index.getIndexImageUrl());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.index.impl.IndexDao#updateIndex(com.hust.model.index.Index)
	 */
	public int updateIndex(Index index) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_index set index_title=?,index_image_url=?,index_image=? where index_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, index.getIndexTitle());
			pstm.setString(2, index.getIndexImageUrl());
			pstm.setString(3, index.getIndexImage());
			pstm.setLong(4, index.getIndexId());				
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.index.impl.IndexDao#deleteIndex(com.hust.model.index.Index)
	 */
	public int deleteIndex(Index index) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_index where index_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, index.getIndexId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.index.impl.IndexDao#queryIndex(java.lang.String, int, int)
	 */
	public List<Index> queryIndex(String keyword,int page,int rows){
		List<Index> result=new ArrayList<Index>();
		con=getConnection(1);
		String sql="select index_id,index_title,index_image,index_image_url from tbl_index where index_title like ? order by index_id asc";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			rs=pstm.executeQuery();
			List<Index> temp=new ArrayList<Index>();
			while (rs.next()){
				Index index=new Index();
				index.setIndexId(rs.getLong("index_id"));
				index.setIndexTitle(rs.getString("index_title"));
				index.setIndexImage(rs.getString("index_image"));
				index.setIndexImageUrl(rs.getString("index_image_url"));
				temp.add(index);
			}
			if (temp.size()>0){
				int j=1;
			    for(int i=rows*(page-1);(i<temp.size()&&j<=rows);i++,j++){
			    	result.add(temp.get(i));
			    }
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.index.impl.IndexDao#queryIndexCount(java.lang.String)
	 */
	public int queryIndexCount(String keyword){
		int result=0;
		con=getConnection(1);
		String sql="select count(*) indexcount from tbl_index where index_title like ? ";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");			
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getInt("indexcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}
	
	public int updatePassword(int userId,String oldPassowrd,String newPassword){
		int result=0;
		con=getConnection(1);
		String sql="update tbl_user set password=? where userid=? and password=? ";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, newPassword);
			pstm.setInt(2, userId);
			pstm.setString(3, oldPassowrd);
			result=pstm.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}

}
