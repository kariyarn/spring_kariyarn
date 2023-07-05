package com.kariyarn.personal.member.dao;

import org.springframework.stereotype.Repository;

import com.kariyarn.personal.member.dto.MemberDto;

public interface MemberDao {
	//아이디가 존재하는지
	public boolean isExist(String inputId);
	//회원가입할 정도를 DB에 저장함
	public void signup(MemberDto dto);
	//아이디로 회원 정보 불러옴(수정, 개인정보 보기에 이용)
	public MemberDto getData(String id);
	//비밀번호를 수정하는 메소드
	public void updatePwd(MemberDto dto);
	//개인정보를 수정하는 메소드
	public void update(MemberDto dto);
	//회원 정보를 삭제
	public void delete(String id);

}
