package com.hust.model.goods;

public class GoodsCat {
	private Long goodsCatId;
	private String goodsCatCode;
	private String goodsCatName;
	private int goodsCatLevel;
	private String goodsCatFatherCode;
	public Long getGoodsCatId() {
		return goodsCatId;
	}
	public String getGoodsCatCode() {
		return goodsCatCode;
	}
	public String getGoodsCatName() {
		return goodsCatName;
	}
	public int getGoodsCatLevel() {
		return goodsCatLevel;
	}
	public String getGoodsCatFatherCode() {
		return goodsCatFatherCode;
	}
	public void setGoodsCatId(Long goodsCatId) {
		this.goodsCatId = goodsCatId;
	}
	public void setGoodsCatCode(String goodsCatCode) {
		this.goodsCatCode = goodsCatCode;
	}
	public void setGoodsCatName(String goodsCatName) {
		this.goodsCatName = goodsCatName;
	}
	public void setGoodsCatLevel(int goodsCatLevel) {
		this.goodsCatLevel = goodsCatLevel;
	}
	public void setGoodsCatFatherCode(String goodsCatFatherCode) {
		this.goodsCatFatherCode = goodsCatFatherCode;
	}
	

}
