package com.kariyarn.personal.movie.dto;

public class MovieReviewDto {
	private int num;
	private String title;
	private String writer;
	private String review;
	private int ref_group;
	private String profile;
	private int rate;
	private int startRowNum;
	private int endRowNum;
	
	public MovieReviewDto() {}


	public MovieReviewDto(int num, String title, String writer, String review, int ref_group, String profile, int rate,
			int startRowNum, int endRowNum) {
		super();
		this.num = num;
		this.title = title;
		this.writer = writer;
		this.review = review;
		this.ref_group = ref_group;
		this.profile = profile;
		this.rate = rate;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRef_group() {
		return ref_group;
	}

	public void setRef_group(int ref_group) {
		this.ref_group = ref_group;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getStartRowNum() {
		return startRowNum;
	}

	public void setStartRowNum(int startRowNum) {
		this.startRowNum = startRowNum;
	}

	public int getEndRowNum() {
		return endRowNum;
	}

	public void setEndRowNum(int endRowNum) {
		this.endRowNum = endRowNum;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
}
