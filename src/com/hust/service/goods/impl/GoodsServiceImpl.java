package com.hust.service.goods.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.goods.GoodsDao;
import com.hust.dao.goods.impl.GoodsDaoImpl;
import com.hust.model.goods.Goods;
import com.hust.model.goods.GoodsCat;
import com.hust.service.goods.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#insertGoods(com.hust.model.goods.Goods)
	 */
	public int insertGoods(Goods goods){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.insertGoods(goods);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#insertGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int insertGoodsCat(GoodsCat goodsCat){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.insertGoodsCat(goodsCat);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#updateGoods(com.hust.model.goods.Goods)
	 */
	public int updateGoods(Goods goods){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.updateGoods(goods);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#updateGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int updateGoodsCat(GoodsCat goodsCat){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.updateGoodsCat(goodsCat);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#deleteGoods(com.hust.model.goods.Goods)
	 */
	public int deleteGoods(Goods goods){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.deleteGoods(goods);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#deleteGoodsCat(com.hust.model.goods.GoodsCat)
	 */
	public int deleteGoodsCat(GoodsCat goodsCat){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.deleteGoodsCat(goodsCat);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoods(java.lang.String, int, int)
	 */
	public List<Goods> queryGoods(String keyword, int page, int rows){
		List<Goods> result=new ArrayList<Goods>();
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoods(keyword, page, rows);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsCat(java.lang.String, int, int)
	 */
	public List<GoodsCat> queryGoodsCat(String keyword, int page, int rows){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsCat(keyword, page, rows);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsFirstLevelCat()
	 */
	public List<GoodsCat> queryGoodsFirstLevelCat(){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsFirstLevelCat();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsSecondLevelCat()
	 */
	public List<GoodsCat> queryGoodsSecondLevelCat(){
		List<GoodsCat> result;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsSecondLevelCat();
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsSecondLevelCat(java.lang.String)
	 */
	public List<GoodsCat> queryGoodsSecondLevelCat(String firstLevelGoodsCatCode){
		List<GoodsCat> result=new ArrayList<GoodsCat>();
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsSecondLevelCat(firstLevelGoodsCatCode);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsCount(java.lang.String)
	 */
	public int queryGoodsCount(String keyword){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsCount(keyword);
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hust.service.goods.impl.GoodsService#queryGoodsCatCount(java.lang.String)
	 */
	public int queryGoodsCatCount(String keyword){
		int result=0;
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.queryGoodsCatCount(keyword);
		return result;
	}
	
	public String getNewGoodsCatCode(int goodsCatLevel,String goodsCatCode){
		String result="";
		GoodsDao goodsDao=new GoodsDaoImpl();
		result=goodsDao.getNewGoodsCatCode(goodsCatLevel,goodsCatCode);
		return result;
	}

}
