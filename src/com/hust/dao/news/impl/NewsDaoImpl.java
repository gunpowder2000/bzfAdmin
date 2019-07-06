package com.hust.dao.news.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.news.NewsDao;
import com.hust.model.news.News;

public class NewsDaoImpl extends BaseDao implements NewsDao {
	/* (non-Javadoc)
	 * @see com.hust.dao.news.impl.NewsDao#insertNews(com.hust.model.news.News)
	 */
	public int insertNews(News news){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_news(news_title,news_digest,news_digest_image,news_content,news_date)values(?,?,?,?,sysdatetime())";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, news.getNewsTitle());
			pstm.setString(2, news.getNewsDigest());
			pstm.setString(3, news.getNewsDigestImage());	
			pstm.setString(4, news.getNewsContent());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.news.impl.NewsDao#updateNews(com.hust.model.news.News)
	 */
	public int updateNews(News news) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_news set news_title=?,news_digest=?,news_content=?,news_date=sysdatetime() where news_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, news.getNewsTitle());
			pstm.setString(2, news.getNewsDigest());
			pstm.setString(3, news.getNewsContent());	
			pstm.setLong(4, news.getNewsId());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.news.impl.NewsDao#deleteNews(com.hust.model.news.News)
	 */
	public int deleteNews(News news) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_news where news_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, news.getNewsId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.news.impl.NewsDao#queryNews(java.lang.String, int, int)
	 */
	public List<News> queryNews(String keyword,int page,int rows){
		List<News> result=new ArrayList<News>();
		con=getConnection(1);
		String sql="select news_id,news_title,news_digest,news_digest_image,news_content,news_date from tbl_news where news_title like ? or news_digest like ? order by news_date desc";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			pstm.setString(2, "%"+keyword+"%");			
			rs=pstm.executeQuery();
			List<News> temp=new ArrayList<News>();
			while (rs.next()){
				News news=new News();
				news.setNewsId(rs.getLong("news_id"));
				news.setNewsTitle(rs.getString("news_title"));
				news.setNewsDigest(rs.getString("news_digest"));
				news.setNewsDigestImage(rs.getString("news_digest_image"));
				news.setNewsContent(rs.getString("news_content"));
				Date tempDate=rs.getDate("news_date");
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				news.setNewsDate(simpleDateFormat.format(tempDate));
				temp.add(news);
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
	 * @see com.hust.dao.news.impl.NewsDao#queryWorksCount(java.lang.String)
	 */
	public int queryNewsCount(String keyword){
		int result=0;
		con=getConnection(1);
		String sql="select count(*) newscount from tbl_news where news_title like ? or news_digest like ?";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			pstm.setString(2, "%"+keyword+"%");			
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getInt("newscount");
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
