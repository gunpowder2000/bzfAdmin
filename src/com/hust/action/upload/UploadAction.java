package com.hust.action.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.sun.media.sound.HsbParser;

public class UploadAction extends BaseAction {
	
	//封装文件标题请求参数的属性
		private String title;
		//封装上传文件域的属性
		private File upload;
		//封装上传文件类型的属性
		private String uploadContentType;
		//封装上传文件名的属性
		private String uploadFileName;
		//直接在struts.xml文件中配置的属性
		private String savePath;
		//接受struts.xml文件配置值的方法
		private Map jsonMap=new HashMap();
		public Map getJsonMap() {
			return jsonMap;
		}
		public void setJsonMap(Map jsonMap) {
			this.jsonMap = jsonMap;
		}
		public void setSavePath(String value)
		{
			this.savePath = value;
		}
		//返回上传文件的保存位置
		private String getSavePath() throws Exception 
		{
			return ServletActionContext.getServletContext()
				.getRealPath(savePath);
			
		}

		//文件标题的setter和getter方法
		public void setTitle(String title) 
		{
			this.title = title; 
		}
		public String getTitle()
		{
			return (this.title); 
		}

		//上传文件对应文件内容的setter和getter方法
		public void setUpload(File upload) 
		{
			this.upload = upload; 
		}
		public File getUpload() 
		{
			return (this.upload); 
		}

		//上传文件的文件类型的setter和getter方法
		public void setUploadContentType(String uploadContentType) 
		{
			this.uploadContentType = uploadContentType; 
		}
		public String getUploadContentType()
		{
			return (this.uploadContentType); 
		}

		//上传文件的文件名的setter和getter方法
		public void setUploadFileName(String uploadFileName) 
		{
			this.uploadFileName = uploadFileName; 
		}
		public String getUploadFileName() 
		{
			return (this.uploadFileName); 
		}
		
		@Override
		public String execute() throws Exception
		{
			//以服务器的文件保存地址和原文件名建立上传文件输出流
			StringBuffer url = request.getRequestURL();  
			String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getContextPath()).toString();
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
			SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("yyyyMMdd");
			Date tempDate=new Date();
			String tempFolder=simpleDateFormat2.format(tempDate);
			String tempFileName=simpleDateFormat.format(tempDate)+getUploadFileName().substring(getUploadFileName().lastIndexOf("."), getUploadFileName().length());					
			File tempFile =new File(getSavePath()+ File.separator + tempFolder);
			if(!tempFile.exists()){
				tempFile.mkdirs();
			}
			/*FileOutputStream fos = new FileOutputStream(getSavePath()
				+ "\\" + getUploadFileName());*/
			FileOutputStream fos = new FileOutputStream(getSavePath()
					+ File.separator + tempFolder+File.separator+tempFileName);
			FileInputStream fis = new FileInputStream(getUpload());
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0)
			{
				fos.write(buffer , 0 , len);
			}
			fos.close();	
			
			jsonMap.put("msg", tempContextUrl+savePath+ "/" + tempFolder+"/"+tempFileName);
			return "result";
		}

}
