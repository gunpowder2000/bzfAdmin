package com.hust.dao.work.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.work.WorkDao;
import com.hust.model.work.Work;

public class WorkDaoImpl extends BaseDao implements WorkDao {
	/* (non-Javadoc)
	 * @see com.hust.dao.work.impl.WorkDao#insertWork(com.hust.model.work.Work)
	 */
	public int insertWork(Work work){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_work(work_title,work_digest,work_content,work_date)values(?,?,?,sysdatetime())";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, work.getWorkTitle());
			pstm.setString(2, work.getWorkDigest());
			pstm.setString(3, work.getWorkContent());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.work.impl.WorkDao#updateWork(com.hust.model.work.Work)
	 */
	public int updateWork(Work work) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_work set work_title=?,work_digest=?,work_content=?,work_date=sysdatetime() where work_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, work.getWorkTitle());
			pstm.setString(2, work.getWorkDigest());
			pstm.setString(3, work.getWorkContent());	
			pstm.setLong(4, work.getWorkId());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.work.impl.WorkDao#deleteWork(com.hust.model.work.Work)
	 */
	public int deleteWork(Work work) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_work where work_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, work.getWorkId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.work.impl.WorkDao#queryWorks(java.lang.String, int, int)
	 */
	public List<Work> queryWorks(String keyword,int page,int rows){
		List<Work> result=new ArrayList<Work>();
		con=getConnection(1);
		String sql="select work_id,work_title,work_digest,work_content,work_date from tbl_work where work_title like ? or work_digest like ? order by work_date desc";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			pstm.setString(2, "%"+keyword+"%");			
			rs=pstm.executeQuery();
			List<Work> temp=new ArrayList<Work>();
			while (rs.next()){
				Work work=new Work();
				work.setWorkId(rs.getLong("work_id"));
				work.setWorkTitle(rs.getString("work_title"));
				work.setWorkDigest(rs.getString("work_digest"));
				work.setWorkContent(rs.getString("work_content"));
				Date tempDate=rs.getDate("work_date");
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				work.setWorkDate(simpleDateFormat.format(tempDate));
				temp.add(work);
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
	 * @see com.hust.dao.work.impl.WorkDao#queryWorksCount(java.lang.String)
	 */
	public int queryWorksCount(String keyword){
		int result=0;
		con=getConnection(1);
		String sql="select count(*) workcount from tbl_work where work_title like ? or work_digest like ?";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			pstm.setString(2, "%"+keyword+"%");			
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getInt("workcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}

}
