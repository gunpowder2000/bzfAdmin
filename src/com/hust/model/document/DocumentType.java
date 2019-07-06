package com.hust.model.document;

public class DocumentType {
	private Long documentTypeId;
	private String documentTypeCode;
	private String documentTypeName;
	public Long getDocumentTypeId() {
		return documentTypeId;
	}
	public String getDocumentTypeCode() {
		return documentTypeCode;
	}
	public String getDocumentTypeName() {
		return documentTypeName;
	}
	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}
	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}

}
