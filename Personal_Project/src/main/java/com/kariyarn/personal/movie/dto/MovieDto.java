package com.kariyarn.personal.movie.dto;

import org.springframework.web.multipart.MultipartFile;

public class MovieDto {
	//영화에 관련한 필드값을 담아두기 위한 DTO이다.
	
	//영화 번호
	private int num;
	//영화 타이틀
	private String title;
	//영화에 대한 설명
	private String caption;
	//영화 포스터 이미지 패스
	private String imagePath;
	//게시글 올린 날짜
	private String regdate;
	//추천수
	private int thumsup;
	//비추수
	private int thumsdown;
	//게시글 페이징 처리를 위한 필드
	private int startRowNum;
	private int endRowNum;
	private int prevRowNum;
	private int nextRowNum;
	private MultipartFile image; //이미지 처리를 위한 필드
	
	public MovieDto() {}

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

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public int getThumsup() {
		return thumsup;
	}

	public void setThumsup(int thumsup) {
		this.thumsup = thumsup;
	}

	public int getThumsdown() {
		return thumsdown;
	}

	public void setThumsdown(int thumsdown) {
		this.thumsdown = thumsdown;
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

	public int getPrevRowNum() {
		return prevRowNum;
	}

	public void setPrevRowNum(int prevRowNum) {
		this.prevRowNum = prevRowNum;
	}

	public int getNextRowNum() {
		return nextRowNum;
	}

	public void setNextRowNum(int nextRowNum) {
		this.nextRowNum = nextRowNum;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public MovieDto(int num, String title, String caption, String imagePath, String regdate, int thumsup, int thumsdown,
			int startRowNum, int endRowNum, int prevRowNum, int nextRowNum, MultipartFile image) {
		super();
		this.num = num;
		this.title = title;
		this.caption = caption;
		this.imagePath = imagePath;
		this.regdate = regdate;
		this.thumsup = thumsup;
		this.thumsdown = thumsdown;
		this.startRowNum = startRowNum;
		this.endRowNum = endRowNum;
		this.prevRowNum = prevRowNum;
		this.nextRowNum = nextRowNum;
		this.image = image;
	}
	
	
}
