package com.hust.action.document;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.hust.action.base.BaseAction;
import com.hust.model.document.Document;
import com.hust.model.user.User;
import com.hust.service.document.DocumentService;
import com.hust.service.document.impl.DocumentServiceImpl;
import com.opensymphony.xwork2.ActionContext;

public class DocumentAction extends BaseAction {
	String msg;
	private Document document;
	
	
	public Document getDocument() {
		return document;
	}


	public void setDocument(Document document) {
		this.document = document;
	}
	

	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String goEditDocument() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		if (user == null) {
			ServletActionContext.getRequest().setAttribute("msg", "登录信息已失效，请注销后重新登录。");
			return "error";
		} else {
			String documentTypeCode=request.getParameter("documentTypeCode");
			DocumentService documentService=new DocumentServiceImpl();
			List<Document> documents=documentService.queryDocuments(documentTypeCode);
			if (documents.size()>0){
				document=documents.get(0);
			}
			

			return "goEditDocument";
		}
	}
	
	public String updateDocument(){
		String documentId=request.getParameter("documentId");
		String documentContent=request.getParameter("documentContent");
		Document document=new Document();
		document.setDocumentContent(documentContent);
		document.setDocumentId(Long.parseLong(documentId));
		DocumentService documentService=new DocumentServiceImpl();
		int i=documentService.updateDocument(document);
		if (i>0) msg="修改成功!";
		return "updateDocument";
	}

}
