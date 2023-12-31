package com.kariyarn.personal.movie.dao;

import java.util.List;

import com.kariyarn.personal.movie.dto.MovieReviewDto;

public interface MovieReviewDao {
	//리뷰 목록 얻어오기
	public List<MovieReviewDto> getList(MovieReviewDto dto);
	//댓글 삭제
	public void delete(int num);
	//댓글 추가
	public void insert(MovieReviewDto dto);
	//추가할 댓글의 글번호를 리턴하는 메소드
	public int getSequence();
	//댓글 수정
	public void update(MovieReviewDto dto);
	//댓글 하나의 정보를 리턴하는 메소드
	public MovieReviewDto getData(int num);
	//댓글의 갯수를 리턴하는 메소드
	public int getCount(int ref_group);
	//리뷰의 평점을 리턴하는 메소드
	public double getAvg(String title);
	//리뷰가 이미 존재하는지 확인하는 메소드
	public MovieReviewDto getEqual(MovieReviewDto dto);
}
