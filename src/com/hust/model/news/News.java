package com.hust.model.news;

public class News {
	private Long newsId;
    private String newsTitle;
    private String newsDigest;
    private String newsDigestImage;
    private String newsContent;
    private String newsDate;
	public Long getNewsId() {
		return newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public String getNewsDigest() {
		return newsDigest;
	}
	public String getNewsDigestImage() {
		return newsDigestImage;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public String getNewsDate() {
		return newsDate;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public void setNewsDigest(String newsDigest) {
		this.newsDigest = newsDigest;
	}
	public void setNewsDigestImage(String newsDigestImage) {
		this.newsDigestImage = newsDigestImage;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
}
