package com.kariyarn.personal.commu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kariyarn.personal.commu.dto.CommuDto;

@Repository
public class CommuDaoImpl implements CommuDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<CommuDto> getList(CommuDto dto) {
		return session.selectList("commu.getList", dto);
	}

	@Override
	public int getCount(CommuDto dto) {
		return session.selectOne("commu.getCount", dto);
	}

	@Override
	public void insert(CommuDto dto) {
		session.insert("commu.insert", dto);
	}

	@Override
	public CommuDto getData(int num) {
		return session.selectOne("commu.getData", num);
	}

	@Override
	public CommuDto getData(CommuDto dto) {
		return session.selectOne("commu.getData", dto);
	}

	@Override
	public void addViewCount(int num) {
		// 나중에 mapper 작성하고 완성
		
	}

	@Override
	public void delete(int num) {
		session.delete("commu.delete", num);
	}

	@Override
	public void update(CommuDto dto) {
		session.update("commu.update", dto);
	}

}
