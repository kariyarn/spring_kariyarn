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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insert(MovieDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public MovieDto getData(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int thumsupCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int thumsdownCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MovieDto dto) {
		// TODO Auto-generated method stub
		
	}

}
