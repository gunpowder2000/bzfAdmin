package com.hust.action.news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.hust.model.index.Index;
import com.hust.model.news.News;
import com.hust.model.user.User;
import com.hust.service.index.IndexService;
import com.hust.service.index.impl.IndexServiceImpl;
import com.hust.service.news.NewsService;
import com.hust.service.news.impl.NewsServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class NewsAction extends BaseAction {
private Map jsonMap=new HashMap();
	

	public Map getJsonMap() {
		return jsonMap;
	}


	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}
	
	
	public String goNewsManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goNewsManage";
		}
	}
	
	public String queryNews() {
		String keyword=request.getParameter("keyword");	
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if (page==null ||"".equals(page)){
			page="1";
		}
		if (rows==null ||"".equals(rows)){
			rows="10";
		}
		List<News> result=null;
		int total=0;
		NewsService newsService=new NewsServiceImpl();
		result=newsService.queryNews(keyword, Integer.parseInt(page), Integer.parseInt(rows));
		total=newsService.queryNewsCount(keyword);
		jsonMap.put("total", total);
		jsonMap.put("rows", result);	
		
		
		return "json";
		
	}
	
	public String editNews() {
		String newsId=request.getParameter("newsId");	
		String newsTitle=request.getParameter("newsTitle");
		String newsDigest=request.getParameter("newsDigest");
		String newsDigestImage=request.getParameter("newsDigestImage");
		String newsContent=request.getParameter("newsContent");
		News news= new News();
		news.setNewsId(Long.parseLong(newsId));
		news.setNewsTitle(newsTitle);
		news.setNewsDigest(newsDigest);
		news.setNewsDigestImage(newsDigestImage);
		news.setNewsContent(newsContent);
		int result=0;
		NewsService newsService=new NewsServiceImpl();
		result=newsService.updateNews(news);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	public String addNews() {		
		String newsTitle=request.getParameter("newsTitle");
		String newsDigest=request.getParameter("newsDigest");
		String newsDigestImage=request.getParameter("newsDigestImage");
		String newsContent=request.getParameter("newsContent");
		News news= new News();		
		news.setNewsTitle(newsTitle);
		news.setNewsDigest(newsDigest);
		news.setNewsDigestImage(newsDigestImage);
		news.setNewsContent(newsContent);
		int result=0;
		NewsService newsService=new NewsServiceImpl();
		result=newsService.insertNews(news);
		jsonMap=new HashMap();
		if (result>0){
			jsonMap.put("msg", "添加成功！");			
		}else {
			jsonMap.put("msg", "添加失败！");
		}
		return "json";
		
	}
	
	public String deleteNews() {		
		String newsId=request.getParameter("newsId");		
		News news= new News();
		news.setNewsId(Long.parseLong(newsId));
		int result=0;
		NewsService newsService=new NewsServiceImpl();
		result=newsService.deleteNews(news);
		if (result>0){
			jsonMap.put("msg", "删除成功！");			
		}else {
			jsonMap.put("msg", "删除失败！");
		}
		return "json";
		
	}

}
