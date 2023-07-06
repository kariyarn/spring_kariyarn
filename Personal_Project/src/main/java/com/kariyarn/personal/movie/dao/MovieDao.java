package com.kariyarn.personal.movie.dao;

import java.util.List;

import com.kariyarn.personal.movie.dto.MovieDto;

public interface MovieDao {
	//모든 영화의 리스트
	public List<MovieDto> getList(MovieDto dto);
	//모든 Row의 갯수
	public int getCount(MovieDto dto);
	//갤러리의 사진 저장하기
	public void insert(MovieDto dto);
	//pk를 이용해 게시글 하나의 data 가져오기
	public MovieDto getData(int num);
	//Sting를 이용해 게시글 하나의 data 가져오기
	public MovieDto getData(String title);
	//추천하기
	public int thumsupCount();
	//비추하기
	public int thumsdownCount();
	//영화 삭제
	public void delete(String id);
	//영화 삭제
	public void update(MovieDto dto);
}
