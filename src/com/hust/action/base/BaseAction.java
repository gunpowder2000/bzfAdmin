package com.hust.action.base;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {	
	
	/**
	 * 
	 */
	public HttpServletRequest request=ServletActionContext.getRequest();
	public HttpServletResponse response=ServletActionContext.getResponse();
	
	private static final long serialVersionUID = 1L;
	private InputStream inputstream;
	private JSONArray jsonArray;
    private JSONObject jsonObject;
	public InputStream getInputstream() {
		return inputstream;
	}

	public void setInputstream(InputStream inputstream) {
		this.inputstream = inputstream;
	}
	
	public void setInputstream(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
		request.setAttribute("json", this.jsonArray);
	}
	
	public void setInputstream(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
		request.setAttribute("json", this.jsonObject);
	}

}
