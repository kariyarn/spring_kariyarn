package com.kariyarn.personal.movie.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kariyarn.personal.movie.dto.MovieDto;

@Repository
public class MovieDaoImpl implements MovieDao{
	
	@Autowired
	private SqlSession session;

	@Override
	public List<MovieDto> getList(MovieDto dto) {
		return session.selectList("movie.getList", dto);
	}

	@Override
	public void insert(MovieDto dto) {
		session.insert("movie.insert", dto);
	}

	@Override
	public MovieDto getData(int num) {
		return session.selectOne("movie.getData", num);
	}

	@Override
	public int thumsupCount() {
		return 0;
	}

	@Override
	public int thumsdownCount() {
		return 0;
	}

	@Override
	public void delete(int num) {
		session.delete("movie.delete", num);
	}

	@Override
	public void update(MovieDto dto) {
		
	}

	@Override
	public MovieDto getData(String title) {
		return session.selectOne("movie.getData2", title);
	}

	@Override
	public int getCount(MovieDto dto) {
		return session.selectOne("movie.getCount", dto);
	}

}
