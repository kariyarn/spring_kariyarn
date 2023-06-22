package com.gura.spring02.guest.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gura.spring02.guest.dto.GuestDto;

//Component scan
@Repository
public class GuestDaoImpl implements GuestDao{
	
	//mybatis 기반으로 DB연동을 하기 위한 핵심 의존객체를 Dependency Injection받는다.	
	@Autowired
	private SqlSession session;
	
	@Override
	public void insert(GuestDto dto) {
		/*
		 * Mapper's namespace=guest
		 * sql's id = insert
		 * parameter type = dto
		 */
		session.insert("guest.insert", dto);
		
	}

	@Override
	public void update(GuestDto dto) {
		session.update("guest.update", dto);
		
	}

	@Override
	public void delete(GuestDto dto) {
		session.delete("guest.delete", dto);
	}

	@Override
	public GuestDto getData(int num) {
		/*
		 * Mapper의 namespace=> guest
		 * sql's id => getData
		 * parameterType => int
		 * resultType => GeustDto
		 */
		GuestDto dto = session.selectOne("guest.getData", num);
		return dto;
	}

	@Override
	public List<GuestDto> getList() {
		List<GuestDto> list = session.selectList("guest.getList");
		return list;
	}

}
