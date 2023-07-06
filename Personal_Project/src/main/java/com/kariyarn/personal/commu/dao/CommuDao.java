package com.kariyarn.personal.commu.dao;

import java.util.List;

import com.kariyarn.personal.commu.dto.CommuDto;

public interface CommuDao {
	//글 목록
	public List<CommuDto> getList(CommuDto dto);
	//글의 갯수
	public int getCount(CommuDto dto);
	//글 추가
	public void insert(CommuDto dto);
	//글 정보 얻어오기
	public CommuDto getData(int num);
	//키워드 활용해서 글정보 얻어오기 
	public CommuDto getData(CommuDto dto);
	//조회수 증가 시키기
	public void addViewCount(int num);
	//글 삭제
	public void delete(int num);
	//글 수정
	public void update(CommuDto dto);
}
