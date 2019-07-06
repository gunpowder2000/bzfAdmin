package com.hust.action.goods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.hust.model.goods.Goods;
import com.hust.model.goods.GoodsCat;
import com.hust.model.user.User;
import com.hust.service.goods.GoodsService;
import com.hust.service.goods.impl.GoodsServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class GoodsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private Map jsonMap=new HashMap();
    private List<GoodsCat> list;
	

	public Map getJsonMap() {
		return jsonMap;
	}


	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
	
	
	public List<GoodsCat> getList() {
		return list;
	}


	public void setList(List<GoodsCat> list) {
		this.list = list;
	}


	public String goGoodsManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goGoodsManage";
		}
	}
	
	public String goGoodsCatManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goGoodsCatManage";
		}
	}
	
	public String deleteGoods() {		
		String goodsId=request.getParameter("goodsId");		
		Goods goods= new Goods();
		goods.setGoodsId(Long.parseLong(goodsId));
		int result=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.deleteGoods(goods);
		if (result>0){
			jsonMap.put("msg", "删除成功！");			
		}else {
			jsonMap.put("msg", "删除失败！");
		}
		return "json";
		
	}
	public String deleteGoodsCat() {		
		String goodsCatId=request.getParameter("goodsCatId");		
		GoodsCat goodsCat= new GoodsCat();
		goodsCat.setGoodsCatId(Long.parseLong(goodsCatId));
		int result=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.deleteGoodsCat(goodsCat);
		if (result>0){
			jsonMap.put("msg", "删除成功！");			
		}else {
			jsonMap.put("msg", "删除失败！");
		}
		return "json";
		
	}
	
	public String queryGoods() {
		String keyword=request.getParameter("keyword");	
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if (page==null ||"".equals(page)){
			page="1";
		}
		if (rows==null ||"".equals(rows)){
			rows="10";
		}
		List<Goods> result=null;
		int total=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.queryGoods(keyword, Integer.parseInt(page), Integer.parseInt(rows));
		total=goodsService.queryGoodsCount(keyword);
		jsonMap.put("total", total);
		jsonMap.put("rows", result);		
		
		return "json";
		
	}
	
	public String queryGoodsCat() {
		String keyword=request.getParameter("keyword");	
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if (page==null ||"".equals(page)){
			page="1";
		}
		if (rows==null ||"".equals(rows)){
			rows="10";
		}
		List<GoodsCat> result=null;
		int total=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.queryGoodsCat(keyword, Integer.parseInt(page), Integer.parseInt(rows));
		total=goodsService.queryGoodsCount(keyword);
		jsonMap.put("total", total);
		jsonMap.put("rows", result);		
		
		return "json";
		
	}
	
	public String  queryGoodsSecondLevelCat() {		
		GoodsService goodsService=new GoodsServiceImpl();
		list=goodsService.queryGoodsSecondLevelCat();
		
		return "list";
		
	}
	
	public String  queryGoodsFirstLevelCat() {		
		GoodsService goodsService=new GoodsServiceImpl();
		list=goodsService.queryGoodsFirstLevelCat();
		
		return "list";
		
	}
	
	public String addGoods(){
		String goodsName=request.getParameter("goodsName");
		String goodsPic=request.getParameter("goodsPic");
		String goodsCatCode =request.getParameter("goodsCatCode");
		String goodsDigest=request.getParameter("goodsDigest");
		String goodsParam=request.getParameter("goodsParam");
		String goodsYinian=request.getParameter("goodsYinian");		
		Goods goods=new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPic(goodsPic);
		goods.setGoodsCatCode(goodsCatCode);
		goods.setGoodsDigest(goodsDigest);
		goods.setGoodsParam(goodsParam);
		goods.setGoodsYinian(goodsYinian);		
		int result=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.insertGoods(goods);
		if (result>0){
			jsonMap.put("msg", "添加成功！");			
		}else {
			jsonMap.put("msg", "添加失败！");
		}
		return "json";
		
	}
	
	public String addGoodsCat(){
		GoodsService goodsService=new GoodsServiceImpl();
		String goodsCatName=request.getParameter("goodsCatName");
		String goodsCatLevel=request.getParameter("goodsCatLevel");
		String goodsCatFatherCode =request.getParameter("goodsCatFatherCode");	
		
		if ("2".equals(goodsCatLevel)){
			if (goodsCatFatherCode==null || "".equals(goodsCatFatherCode)){
				jsonMap.put("msg", "二级分类必须设定父类！");
				return "json";
			}
		}else{
			goodsCatFatherCode="0";
		}
		String goodsCatCode=goodsService.getNewGoodsCatCode(Integer.parseInt(goodsCatLevel),goodsCatFatherCode);
		GoodsCat goodsCat=new GoodsCat();
		goodsCat.setGoodsCatName(goodsCatName);
		goodsCat.setGoodsCatLevel(Integer.parseInt(goodsCatLevel));
		goodsCat.setGoodsCatFatherCode(goodsCatFatherCode);
		goodsCat.setGoodsCatCode(goodsCatCode);			
		int result=0;		
		result=goodsService.insertGoodsCat(goodsCat);
		if (result>0){
			jsonMap.put("msg", "添加成功！");			
		}else {
			jsonMap.put("msg", "添加失败！");
		}
		return "json";
		
	}
	
	public String editGoods(){
		String goodsName=request.getParameter("goodsName");
		String goodsPic=request.getParameter("goodsPic");
		String goodsCatCode =request.getParameter("goodsCatCode");
		String goodsDigest=request.getParameter("goodsDigest");
		String goodsParam=request.getParameter("goodsParam");
		String goodsYinian=request.getParameter("goodsYinian");
		String goodsId=request.getParameter("goodsId");
		Goods goods=new Goods();
		goods.setGoodsName(goodsName);
		goods.setGoodsPic(goodsPic);
		goods.setGoodsCatCode(goodsCatCode);
		goods.setGoodsDigest(goodsDigest);
		goods.setGoodsParam(goodsParam);
		goods.setGoodsYinian(goodsYinian);
		goods.setGoodsId(Long.parseLong(goodsId));
		int result=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.updateGoods(goods);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	
	public String editGoodsCat(){
		String goodsCatId=request.getParameter("goodsCatId");
		String goodsCatName=request.getParameter("goodsCatName");		
		GoodsCat goodsCat=new GoodsCat();
		goodsCat.setGoodsCatId(Long.parseLong(goodsCatId));
		goodsCat.setGoodsCatName(goodsCatName);		
		int result=0;
		GoodsService goodsService=new GoodsServiceImpl();
		result=goodsService.updateGoodsCat(goodsCat);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	
	
	
	

}
