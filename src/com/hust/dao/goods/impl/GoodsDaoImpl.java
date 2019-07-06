package com.hust.dao.goods.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hust.dao.base.BaseDao;
import com.hust.dao.goods.GoodsDao;
import com.hust.model.goods.Goods;
import com.hust.model.goods.GoodsCat;

public class GoodsDaoImpl extends BaseDao implements GoodsDao {
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#insertGoods(com.hust.model.goods.Goods)
	 */
	public int insertGoods(Goods goods){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_goods(goods_name,goods_digest,goods_pic,goods_param,goods_yinian,goods_cat_code)values(?,?,?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, goods.getGoodsName());
			pstm.setString(2, goods.getGoodsDigest());
			pstm.setString(3, goods.getGoodsPic());
			pstm.setString(4, goods.getGoodsParam());
			pstm.setString(5, goods.getGoodsYinian());
			pstm.setString(6, goods.getGoodsCatCode());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#insertGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int insertGoodsCat(GoodsCat goodsCat){
		int result=0;
		try {
			con = getConnection(1);
			String sql = "insert into tbl_goods_cat(goods_cat_code,goods_cat_name,goods_cat_level,goods_cat_father_code)values(?,?,?,?)";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, goodsCat.getGoodsCatCode());
			pstm.setString(2, goodsCat.getGoodsCatName());
			pstm.setInt(3, goodsCat.getGoodsCatLevel());
			pstm.setString(4, goodsCat.getGoodsCatFatherCode());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#updateGoods(com.hust.model.goods.Goods)
	 */
	public int updateGoods(Goods goods) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_goods set goods_name=?,goods_digest=?,goods_param=?,goods_yinian=?,goods_cat_code=?,goods_pic=? where goods_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, goods.getGoodsName());
			pstm.setString(2, goods.getGoodsDigest());
			pstm.setString(3, goods.getGoodsParam());
			pstm.setString(4, goods.getGoodsYinian());
			pstm.setString(5, goods.getGoodsCatCode());
			pstm.setString(6, goods.getGoodsPic());
			pstm.setLong(7, goods.getGoodsId());
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#updateGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int updateGoodsCat(GoodsCat goodsCat) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "update tbl_goods_cat set goods_cat_name=? where goods_cat_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1, goodsCat.getGoodsCatName());
			pstm.setLong(2, goodsCat.getGoodsCatId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#deleteGoods(com.hust.model.goods.Goods)
	 */
	public int deleteGoods(Goods goods) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_goods where goods_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, goods.getGoodsId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#deleteGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int deleteGoodsCat(GoodsCat goodsCat) {
		int result=0;
		try {
			con = getConnection(1);
			String sql = "delete tbl_goods_cat where goods_cat_id=?";
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, goodsCat.getGoodsCatId());			
			result=pstm.executeUpdate();			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			freeConnection();
		}
		return result;
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoods(java.lang.String, int, int)
	 */
	public List<Goods> queryGoods(String keyword,int page,int rows){
		List<Goods> result=new ArrayList<Goods>();
		con=getConnection(1);
		String sql="select goods_id,goods_name,goods_digest,goods_pic,goods_param,goods_yinian,a.goods_cat_code,goods_cat_name from tbl_goods a left join tbl_goods_cat b on a.goods_cat_code=b.goods_cat_code where goods_name like ? or b.goods_cat_name like ? order by goods_id asc";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			pstm.setString(2, "%"+keyword+"%");
			rs=pstm.executeQuery();
			List<Goods> temp=new ArrayList<Goods>();
			while (rs.next()){
				Goods goods=new Goods();
				goods.setGoodsId(rs.getLong("goods_id"));
				goods.setGoodsName(rs.getString("goods_name"));
				goods.setGoodsDigest(rs.getString("goods_digest"));
				goods.setGoodsPic(rs.getString("goods_pic"));
				goods.setGoodsParam(rs.getString("goods_param"));
				goods.setGoodsYinian(rs.getString("goods_yinian"));
				goods.setGoodsCatCode(rs.getString("goods_cat_code"));
				goods.setGoodsCatName(rs.getString("goods_cat_name"));
				temp.add(goods);
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsCat(java.lang.String, int, int)
	 */
	public List<GoodsCat> queryGoodsCat(String keyword,int page,int rows){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		con=getConnection(1);
		String sql="select goods_cat_id,goods_cat_code,goods_cat_name,goods_cat_level,goods_cat_father_code from tbl_goods_cat a order by goods_cat_code asc";		
		try {
			pstm=con.prepareStatement(sql);			
			rs=pstm.executeQuery();
			List<GoodsCat> temp=new ArrayList<GoodsCat>();
			while (rs.next()){
				GoodsCat goodsCat=new GoodsCat();
				goodsCat.setGoodsCatId(rs.getLong("goods_cat_id"));
				goodsCat.setGoodsCatCode(rs.getString("goods_cat_code"));
				goodsCat.setGoodsCatName(rs.getString("goods_cat_name"));
				goodsCat.setGoodsCatLevel(rs.getInt("goods_cat_level"));
				goodsCat.setGoodsCatFatherCode(rs.getString("goods_cat_father_code"));								
				temp.add(goodsCat);
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsFirstLevelCat()
	 */
	public List<GoodsCat> queryGoodsFirstLevelCat(){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		con=getConnection(1);
		String sql="select goods_cat_id,goods_cat_code,goods_cat_name,goods_cat_level,goods_cat_father_code from tbl_goods_cat a where a.goods_cat_level=1 order by goods_cat_code asc";		
		try {
			pstm=con.prepareStatement(sql);			
			rs=pstm.executeQuery();			
			while (rs.next()){
				GoodsCat goodsCat=new GoodsCat();
				goodsCat.setGoodsCatId(rs.getLong("goods_cat_id"));
				goodsCat.setGoodsCatCode(rs.getString("goods_cat_code"));
				goodsCat.setGoodsCatName(rs.getString("goods_cat_name"));
				goodsCat.setGoodsCatLevel(rs.getInt("goods_cat_level"));
				goodsCat.setGoodsCatFatherCode(rs.getString("goods_cat_father_code"));								
				result.add(goodsCat);
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsSecondLevelCat()
	 */
	public List<GoodsCat> queryGoodsSecondLevelCat(){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		con=getConnection(1);
		String sql="select goods_cat_id,goods_cat_code,goods_cat_name,goods_cat_level,goods_cat_father_code from tbl_goods_cat a where a.goods_cat_level=2 order by goods_cat_code asc";		
		try {
			pstm=con.prepareStatement(sql);			
			rs=pstm.executeQuery();			
			while (rs.next()){
				GoodsCat goodsCat=new GoodsCat();
				goodsCat.setGoodsCatId(rs.getLong("goods_cat_id"));
				goodsCat.setGoodsCatCode(rs.getString("goods_cat_code"));
				goodsCat.setGoodsCatName(rs.getString("goods_cat_name"));
				goodsCat.setGoodsCatLevel(rs.getInt("goods_cat_level"));
				goodsCat.setGoodsCatFatherCode(rs.getString("goods_cat_father_code"));								
				result.add(goodsCat);
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsSecondLevelCat(java.lang.String)
	 */
	public List<GoodsCat> queryGoodsSecondLevelCat(String firstLevelGoodsCatCode){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		con=getConnection(1);
		String sql="select goods_cat_id,goods_cat_code,goods_cat_name,goods_cat_level,goods_cat_father_code from tbl_goods_cat a where a.goods_cat_level=2 and a.goods_cat_father_code=? order by goods_cat_code asc";		
		try {
			pstm=con.prepareStatement(sql);	
			pstm.setString(1, firstLevelGoodsCatCode);
			rs=pstm.executeQuery();
			List<GoodsCat> temp=new ArrayList<GoodsCat>();
			while (rs.next()){
				GoodsCat goodsCat=new GoodsCat();
				goodsCat.setGoodsCatId(rs.getLong("goods_cat_id"));
				goodsCat.setGoodsCatCode(rs.getString("goods_cat_code"));
				goodsCat.setGoodsCatName(rs.getString("goods_cat_name"));
				goodsCat.setGoodsCatLevel(rs.getInt("goods_cat_level"));
				goodsCat.setGoodsCatFatherCode(rs.getString("goods_cat_father_code"));								
				temp.add(goodsCat);
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsCount(java.lang.String)
	 */
	public int queryGoodsCount(String keyword){
		int result=0;
		con=getConnection(1);
		String sql="select count(*) goodscount from tbl_goods a left join tbl_goods_cat b on a.goods_cat_code=b.goods_cat_code where goods_name like ? or b.goods_cat_name like ? ";		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");	
			pstm.setString(2, "%"+keyword+"%");	
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getInt("goodscount");
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
	 * @see com.hust.dao.goods.impl.GoodsDao#queryGoodsCatCount(java.lang.String)
	 */
	public int queryGoodsCatCount(String keyword){
		int result=0;
		con=getConnection(1);
		String sql="select count(*) goodscatcount from tbl_goods_cat ";		
		try {
			pstm=con.prepareStatement(sql);			
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getInt("goodscatcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}
	
	public String getNewGoodsCatCode(int goodsCatLevel,String goodsCatCode){
		String result="";
		con=getConnection(1);
		String sql="select max(goods_cat_code) goodsCatCode from tbl_goods_cat where goods_cat_level=? and goods_cat_father_code=? ";		
		try {
			pstm=con.prepareStatement(sql);		
			pstm.setInt(1, goodsCatLevel);
			pstm.setString(2, goodsCatCode);
			rs=pstm.executeQuery();
			while (rs.next()){
				result=rs.getString("goodsCatCode");				
			}
			
			if (goodsCatLevel==1){
				if(result == null || "".equals(result)){
					result = "1000";
				}
			} else {
				if(result == null || "".equals(result)){
					result = goodsCatCode + "000";
				}
			}
			
			
			Integer tempInt=Integer.parseInt(result);
			String tempstr=String.valueOf(tempInt+1);
			
			/*if (goodsCatLevel==1){
				while(tempstr.length()<4){
					tempstr="0"+tempstr;
				}
			}else{
				while(tempstr.length()<7){
					tempstr= "0"tempstr;
				}
			}*/
			result=tempstr;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			freeConnection();
		}
		return result;
	}

}
