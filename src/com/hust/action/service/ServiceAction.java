package com.hust.action.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.hust.dao.rolemenu.RoleMenuDao;
import com.hust.dao.rolemenu.impl.RoleMenuDaoImpl;

import com.hust.model.job.Job;
import com.hust.model.role.Role;
import com.hust.model.user.User;
import com.hust.service.job.JobService;
import com.hust.service.job.impl.JobServiceImpl;
import com.hust.service.rolemenu.RoleMenuService;
import com.hust.service.rolemenu.impl.RoleMenuServiceImpl;
import com.hust.service.user.UserService;
import com.hust.service.user.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class ServiceAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String goServiceManage() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {

			return "goServiceManage";
		}
	}
	
	public String gomanagerolemenu() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			RoleMenuService roleMenuService=new RoleMenuServiceImpl();
			String roleid=request.getParameter("roleid");
			request.setAttribute("rolemenu", roleMenuService.getRoleMenuByRoleId(roleid));
			request.setAttribute("roleid", roleid);

			return "gomanagerolemenu";
		}
	}

	public String getJob() {
		List<Job> result = null;
		JobService jobdao = new JobServiceImpl();
		result = jobdao.getJob();
		JSONArray jsonObject = null;
		jsonObject = JSONArray.fromObject(result);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "getJob";
	}
	
	public String getRole() {
		List<Role> result = null;
		RoleMenuDao roleMenuDao = new RoleMenuDaoImpl();
		result = roleMenuDao.getRole();
		JSONArray jsonObject = null;
		jsonObject = JSONArray.fromObject(result);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "getRole";
	}

	public String doSearchUser() {		
		String key = request.getParameter("keyword");
		List<User> result = null;
		UserService userdao = new UserServiceImpl();
		result = userdao.searchUser(key);
		JSONArray jsonObject = null;
		jsonObject = JSONArray.fromObject(result);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "doSearchUser";
	}
	
	public String addUser() {		
		String usernumber = request.getParameter("usernumber");
		String username = request.getParameter("username");
		String jobid = request.getParameter("jobid");
		String phone = request.getParameter("phone");
		String password = request.getParameter("password");
		String memo = request.getParameter("memo");	
		String roleid=request.getParameter("roleid");
		User user=new User();
		user.setUsernumber(usernumber);
		user.setUsername(username);
		user.setJobid(jobid);
		user.setPhone(phone);
		user.setPassword(password);
		user.setMemo(memo);
		user.setRoleid(Integer.parseInt(roleid));
		int result=0;
		UserService userdao = new UserServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		result = userdao.addUser(user);
		if (result>0) {
			map.put("success", Boolean.TRUE);
			map.put("msg", "添加成功！");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("msg", "添加失败！");
		}
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(map);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "addUser";
	}
	
	public String editUser() {
		String userid = request.getParameter("userid");
		String usernumber = request.getParameter("usernumber");
		String username = request.getParameter("username");
		String jobid = request.getParameter("jobid");
		String phone = request.getParameter("phone");		
		String memo = request.getParameter("memo");	
		String roleid=request.getParameter("roleid");
		User user=new User();
		user.setUserid(Integer.parseInt(userid));
		user.setUsernumber(usernumber);
		user.setUsername(username);
		user.setJobid(jobid);
		user.setPhone(phone);		
		user.setMemo(memo);
		user.setRoleid(Integer.parseInt(roleid));
		int result=0;
		UserService userdao = new UserServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		result = userdao.editUser(user);
		if (result>0) {
			map.put("success", Boolean.TRUE);
			map.put("msg", "编辑成功！");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("msg", "编辑失败！");
		}
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(map);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "editUser";
	}
	
	public String cancelUser() {		
		String userid = request.getParameter("userid");			
		int result=0;
		UserService userdao = new UserServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		result = userdao.cancelUser(Long.parseLong(userid));
		if (result>0) {
			map.put("success", Boolean.TRUE);
			map.put("msg", "注销成功！");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("msg", "注销失败！");
		}
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(map);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "cancelUser";
	}
	
	public String saveRoleMenu(){
		String roleid = request.getParameter("roleid");		
		String menuids=request.getParameter("menuids");
		int result=0;
		RoleMenuService roleMenuService = new RoleMenuServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		result = roleMenuService.saveRoleMenu(Integer.parseInt(roleid), menuids);
		if (result>0) {
			map.put("success", Boolean.TRUE);
			map.put("msg", "保存成功！");
		} else {
			map.put("success", Boolean.FALSE);
			map.put("msg", "保存失败！");
		}
		JSONObject jsonObject = null;
		jsonObject = JSONObject.fromObject(map);
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		return "saveRoleMenu";
	}

}
