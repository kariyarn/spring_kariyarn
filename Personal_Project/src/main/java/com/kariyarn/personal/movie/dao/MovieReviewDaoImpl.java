package com.kariyarn.personal.movie.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kariyarn.personal.movie.dto.MovieDto;
import com.kariyarn.personal.movie.dto.MovieReviewDto;

@Repository
public class MovieReviewDaoImpl implements MovieReviewDao{

	@Autowired
	private SqlSession session;

	@Override
	public List<MovieReviewDto> getList(MovieReviewDto dto) {
		return session.selectList("movieReview.getList", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("movieReview.delete", num);
	}

	@Override
	public void insert(MovieReviewDto dto) {
		session.insert("movieReview.insert", dto);
	}

	@Override
	public int getSequence() {
		return session.selectOne("movieReview.getSequence");
	}

	@Override
	public void update(MovieReviewDto dto) {
		session.update("movieReview.update", dto);
	}

	@Override
	public MovieReviewDto getData(int num) {
		return session.selectOne("movieReview.getData", num);
	}

	@Override
	public int getCount(int ref_group) {
		return session.selectOne("movieReview.getCount", ref_group);
	}
}
