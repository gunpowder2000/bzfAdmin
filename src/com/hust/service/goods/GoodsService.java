package com.hust.service.goods;

import java.util.List;

import com.hust.model.goods.Goods;
import com.hust.model.goods.GoodsCat;

public interface GoodsService {

	public abstract int insertGoods(Goods goods);

	public abstract int insertGoodsCat(GoodsCat goodsCat);

	public abstract int updateGoods(Goods goods);

	public abstract int updateGoodsCat(GoodsCat goodsCat);

	public abstract int deleteGoods(Goods goods);

	public abstract int deleteGoodsCat(GoodsCat goodsCat);

	public abstract List<Goods> queryGoods(String keyword, int page, int rows);

	public abstract List<GoodsCat> queryGoodsCat(String keyword, int page, int rows);

	public abstract List<GoodsCat> queryGoodsFirstLevelCat();

	public abstract List<GoodsCat> queryGoodsSecondLevelCat();

	public abstract List<GoodsCat> queryGoodsSecondLevelCat(String firstLevelGoodsCatCode);

	public abstract int queryGoodsCount(String keyword);

	public abstract int queryGoodsCatCount(String keyword);
	
	public abstract String getNewGoodsCatCode(int goodsCatLevel,String goodsCatCode);

}