package com.hust.service.document;

import java.util.List;

import com.hust.model.document.Document;

public interface DocumentService {

	public abstract List<Document> queryDocuments(String documentTypeCode);

	public abstract int updateDocument(Document document);

}