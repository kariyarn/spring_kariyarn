package com.kariyarn.personal.member.dto;

public class MemberDto {
	private int num;
	private String id;
	private String pwd;
	private String profile;
	private String email;
	private String intro;
	private String regdate;
	private String newPwd;
	
	public MemberDto() {}

	
	
	public MemberDto(int num, String id, String pwd, String profile, String email, String intro, String regdate,
			String newPwd) {
		super();
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.profile = profile;
		this.email = email;
		this.intro = intro;
		this.regdate = regdate;
		this.newPwd = newPwd;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	
	
	
}
