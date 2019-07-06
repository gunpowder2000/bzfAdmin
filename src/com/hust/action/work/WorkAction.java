package com.hust.action.work;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import com.hust.action.base.BaseAction;
import com.hust.model.user.User;
import com.hust.model.work.Work;
import com.hust.service.work.WorkService;
import com.hust.service.work.impl.WorkServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class WorkAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Map<String, Object> jsonMap = new HashMap<String, Object>();
	

	public Map<String, Object> getJsonMap() {
		return jsonMap;
	}

	public void setJsonMap(Map<String, Object> jsonMap) {
		this.jsonMap = jsonMap;
	}

	public String goWorkManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			return "goWorkManage";
		}
	}
	
	public String queryWorks() {
		String keyword=request.getParameter("keyword");	
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		if (page==null ||"".equals(page)){
			page="1";
		}
		if (rows==null ||"".equals(rows)){
			rows="20";
		}
		List<Work> result=null;
		int total=0;
		WorkService workService=new WorkServiceImpl();
		result=workService.queryWorks(keyword, Integer.parseInt(page), Integer.parseInt(rows));
		total=workService.queryWorksCount(keyword);
		jsonMap.put("total", total);
		jsonMap.put("rows", result);	
		
		
		return "json";
		
	}
	
	public String editWork() {
		String workId=request.getParameter("workId");	
		String workTitle=request.getParameter("workTitle");
		String workDigest=request.getParameter("workDigest");
		String workContent=request.getParameter("workContent");		
		Work work= new Work();
		work.setWorkContent(workContent);
		work.setWorkDigest(workDigest);
		work.setWorkId(Long.parseLong(workId));
		work.setWorkTitle(workTitle);
		int result=0;
		WorkService workService=new WorkServiceImpl();
		result=workService.updateWork(work);
		if (result>0){
			jsonMap.put("msg", "修改成功！");			
		}else {
			jsonMap.put("msg", "修改失败！");
		}
		return "json";
		
	}
	public String addWork() {		
		String workTitle=request.getParameter("workTitle");
		String workDigest=request.getParameter("workDigest");
		String workContent=request.getParameter("workContent");		
		Work work= new Work();
		work.setWorkContent(workContent);
		work.setWorkDigest(workDigest);		
		work.setWorkTitle(workTitle);
		int result=0;
		WorkService workService=new WorkServiceImpl();
		result=workService.insertWork(work);
		if (result>0){
			jsonMap.put("msg", "添加成功！");			
		}else {
			jsonMap.put("msg", "添加失败！");
		}
		return "json";
		
	}
	
	public String deleteWork() {		
		String workId=request.getParameter("workId");		
		Work work= new Work();
		work.setWorkId(Long.parseLong(workId));
		int result=0;
		WorkService workService=new WorkServiceImpl();
		result=workService.deleteWork(work);
		if (result>0){
			jsonMap.put("msg", "删除成功！");			
		}else {
			jsonMap.put("msg", "删除失败！");
		}
		return "json";
		
	}

}
