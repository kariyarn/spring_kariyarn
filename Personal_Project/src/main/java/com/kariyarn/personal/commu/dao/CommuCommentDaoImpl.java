package com.kariyarn.personal.commu.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kariyarn.personal.commu.dto.CommuCommentDto;

@Repository
public class CommuCommentDaoImpl implements CommuCommentDao{

	@Autowired
	private SqlSession session;

	@Override
	public List<CommuCommentDto> getList(CommuCommentDto dto) {
		return session.selectList("commuComment.getList", dto);
	}

	@Override
	public void delete(int num) {
		session.update("commuComment.delete", num);
	}

	@Override
	public void insert(CommuCommentDto dto) {
		session.insert("commuComment.insert", dto);
	}

	@Override
	public int getSequence() {
		return session.selectOne("commuComment.getSequence");
	}

	@Override
	public void update(CommuCommentDto dto) {
		session.update("commuComment.update", dto);
	}

	@Override
	public CommuCommentDto getData(int num) {
		return session.selectOne("commuComment.getData", num);
	}

	@Override
	public int getCount(int ref_group) {
		return session.selectOne("commuComment.getCount", ref_group);
	}
}
