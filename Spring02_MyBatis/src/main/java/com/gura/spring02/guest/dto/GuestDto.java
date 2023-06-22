package com.gura.spring02.guest.dto;

public class GuestDto {
	private int num;
	private String writer;
	private String pwd;
	private String content;
	private String regdate;
	
	public GuestDto() {}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public GuestDto(int num, String writer, String pwd, String content, String regdate) {
		super();
		this.num = num;
		this.writer = writer;
		this.pwd = pwd;
		this.content = content;
		this.regdate = regdate;
	}
}
