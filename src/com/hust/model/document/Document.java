package com.hust.model.document;

public class Document {
	private Long documentId;
	private String documentTitle;
	private String documentTypeCode;
	private String documentContent;
	private String documentDigest;
	public Long getDocumentId() {
		return documentId;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public String getDocumentTypeCode() {
		return documentTypeCode;
	}
	public String getDocumentContent() {
		return documentContent;
	}
	public String getDocumentDigest() {
		return documentDigest;
	}
	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public void setDocumentTypeCode(String documentTypeCode) {
		this.documentTypeCode = documentTypeCode;
	}
	public void setDocumentContent(String documentContent) {
		this.documentContent = documentContent;
	}
	public void setDocumentDigest(String documentDigest) {
		this.documentDigest = documentDigest;
	}
	

}
