package com.kariyarn.personal.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kariyarn.personal.member.dto.MemberDto;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public boolean isExist(String inputId) {
		MemberDto dto = session.selectOne("member.getData", inputId);
		boolean isExist = dto == null ? false:true;
		return isExist;
	}

	@Override
	public void signup(MemberDto dto) {
		//어떤 namespace에 있는 어떤 메소드를 찾아줘야함
		session.insert("member.insert", dto);
	}

	@Override
	public MemberDto getData(String id) {
		return session.selectOne("member.getData", id);
	}

	@Override
	public void update(MemberDto dto) {
		session.update("member.update", dto);
		
	}

	@Override
	public void delete(String id) {
		session.delete("member.delete", id);
		
	}

	@Override
	public void updatePwd(MemberDto dto) {
		session.update("member.updatePwd", dto);
		
	}

}
