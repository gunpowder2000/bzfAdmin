package com.hust.action.index;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.hust.model.index.Index;
import com.hust.model.news.News;
import com.hust.model.user.User;
import com.hust.model.work.Work;
import com.hust.service.index.IndexService;
import com.hust.service.index.impl.IndexServiceImpl;
import com.hust.service.work.WorkService;
import com.hust.service.work.impl.WorkServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class IndexAction extends BaseAction {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map jsonMap=new HashMap();
	

	public Map getJsonMap() {
		return jsonMap;
	}


	public void setJsonMap(Map jsonMap) {
		this.jsonMap = jsonMap;
	}


	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		User user = (User) ctx.getSession().get("user");
		if (user == null)
			return "unsigned";
		else
			return "signed";

	}
	
	public String goIndexManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goIndexManage";
		}
	}
	
	public String goUpdatePassword() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goUpdatePassword";
		}
	}
	
	public String queryIndex() {
		String keyword=request.getParameter("keyword");	
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if (page==null ||"".equals(page)){
			page="1";
		}
		if (rows==null ||"".equals(rows)){
			rows="10";
		}
		List<Index> result=null;
		int total=0;
		IndexService indexService=new IndexServiceImpl();
		result=indexService.queryIndex(keyword, Integer.parseInt(page), Integer.parseInt(rows));
		total=indexService.queryIndexCount(keyword);
		jsonMap.put("total", total);
		jsonMap.put("rows", result);	
		
		
		return "json";
		
	}
	
	public String editIndex() {
		String indexId=request.getParameter("indexId");	
		String indexTitle=request.getParameter("indexTitle");
		String indexImage=request.getParameter("indexImage");
		String indexImageUrl=request.getParameter("indexImageUrl");
		Index index= new Index();
		index.setIndexId(Long.parseLong(indexId));
		index.setIndexTitle(indexTitle);
		index.setIndexImageUrl(indexImageUrl);
		index.setIndexImage(indexImage);
		int result=0;
		IndexService indexService=new IndexServiceImpl();
		result=indexService.updateIndex(index);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	public String addIndex() {		
		String indexTitle=request.getParameter("indexTitle");
		String indexImage=request.getParameter("indexImage");
		String indexImageUrl=request.getParameter("indexImageUrl");		
		Index index= new Index();
		index.setIndexTitle(indexTitle);
		index.setIndexImage(indexImage);
		index.setIndexImageUrl(indexImageUrl);
		int result=0;
		IndexService indexService=new IndexServiceImpl();
		result=indexService.insertIndex(index);
		jsonMap=new HashMap();
		if (result>0){
			jsonMap.put("msg", "添加成功！");			
		}else {
			jsonMap.put("msg", "添加失败！");
		}
		return "json";
		
	}
	
	public String deleteIndex() {		
		String indexId=request.getParameter("indexId");		
		Index index= new Index();
		index.setIndexId(Long.parseLong(indexId));
		int result=0;
		IndexService indexService=new IndexServiceImpl();
		result=indexService.deleteIndex(index);
		if (result>0){
			jsonMap.put("msg", "删除成功！");			
		}else {
			jsonMap.put("msg", "删除失败！");
		}
		return "json";
		
	}
	
	public String updatePassword() {
		String oldPassword=request.getParameter("oldPassword");	
		String newPassword=request.getParameter("newPassword");
		String verifyPassword=request.getParameter("verifyPassword");
		if (newPassword==null ||"".equals(newPassword)){
			jsonMap.put("msg", "请输入新密码！");
			return "json";
		}
		if (newPassword!=null && oldPassword!=null && !newPassword.equals(verifyPassword)){
			jsonMap.put("msg", "新密码和确认密码不一致！");
			return "json";
		}
		User user= (User)request.getSession().getAttribute("user");		
		int result=0;
		IndexService indexService=new IndexServiceImpl();		
		result=indexService.updatePassword(user.getUserid(), oldPassword, newPassword);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	
	
	
}
