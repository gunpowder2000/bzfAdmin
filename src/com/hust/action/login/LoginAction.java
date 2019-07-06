package com.hust.action.login;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;



import com.hust.action.base.BaseAction;
import com.hust.dao.menu.MenuDao;
import com.hust.dao.menu.impl.MenuDaoImpl;
import com.hust.model.menu.Menu;
import com.hust.model.user.User;
import com.hust.service.user.UserService;
import com.hust.service.user.impl.UserServiceImpl;

public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userNumber;
	private String password;
	

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String execute() {
		UserService userdao = new UserServiceImpl();
		User user = userdao.getUser(userNumber);

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject=null;
		if (user == null) {
			map.put("success", Boolean.FALSE);
			map.put("msg", "账号输入错误！");
		} else {
			if (!"1".equals(user.getState())) {
				map.put("success", Boolean.FALSE);
				map.put("msg", "用户已失效！");
			} else if (!password.equals(user.getPassword())) {
				map.put("success", Boolean.FALSE);
				map.put("msg", "密码输入错误！");
			} else {				
				MenuDao menudao=new MenuDaoImpl();
				List<Menu> list=menudao.getMenuByUser(user.getUserid());
				ServletActionContext.getRequest().getSession().setAttribute("user", user);
				ServletActionContext.getRequest().getSession().setAttribute("menu", list);
				map.put("success", Boolean.TRUE);				
			}
		}
		jsonObject=JSONObject.fromObject(map);	
		//setInputstream(new ByteArrayInputStream(jsonObject.toString().getBytes("UTF-8")));
		setInputstream(jsonObject);
		//HttpServletRequest request=ServletActionContext.getRequest();
		//request.setAttribute("jsonObject", jsonObject);
		return SUCCESS;

	}
	public String logout(){
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		ServletActionContext.getRequest().getSession().removeAttribute("menu");
		return "logout";
	}

}
