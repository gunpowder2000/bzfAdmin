package com.hust.model.work;

import java.sql.Date;

public class Work {
	private Long workId;
	private String workTitle;
	private String workDigest;
	private String workContent;
	private String workDate;
	public Long getWorkId() {
		return workId;
	}
	public String getWorkTitle() {
		return workTitle;
	}
	public String getWorkDigest() {
		return workDigest;
	}
	public String getWorkContent() {
		return workContent;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	public void setWorkTitle(String workTitle) {
		this.workTitle = workTitle;
	}
	public void setWorkDigest(String workDigest) {
		this.workDigest = workDigest;
	}
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	

}
