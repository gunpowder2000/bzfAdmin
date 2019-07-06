package com.hust.dao.document;

import java.util.List;

import com.hust.model.document.Document;

public interface DocumentDao {

	public abstract List<Document> queryDocuments(String documentTypeCode);

	public abstract int updateDocument(Document document);

}