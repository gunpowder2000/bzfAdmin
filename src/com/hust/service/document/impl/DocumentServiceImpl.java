package com.hust.service.document.impl;

import java.util.ArrayList;
import java.util.List;

import com.hust.dao.document.DocumentDao;
import com.hust.dao.document.impl.DocumentDaoImpl;
import com.hust.model.document.Document;
import com.hust.service.document.DocumentService;

public class DocumentServiceImpl implements DocumentService {
	/* (non-Javadoc)
	 * @see com.hust.service.document.impl.DocumentService#queryDocuments(java.lang.String)
	 */
	public List<Document> queryDocuments(String documentTypeCode){
		List<Document> result=new ArrayList<Document>();
		DocumentDao documentDao=new DocumentDaoImpl();
		result=documentDao.queryDocuments(documentTypeCode);
		return result;
	}
	
	/* (non-Javadoc)
	 * @see com.hust.service.document.impl.DocumentService#updateDocument(com.hust.model.document.Document)
	 */
	public int updateDocument(Document document){
		int result=0;
		DocumentDao documentDao=new DocumentDaoImpl();
		result=documentDao.updateDocument(document);
		return result;
	}

}
