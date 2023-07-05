package com.kariyarn.personal.movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.movie.dto.MovieDto;


public interface MovieService {
	//list가져오기
	public void getList(HttpServletRequest request);
	//사진 upload*DB 저장하기
	public void saveImage(MovieDto dto, HttpServletRequest request);
	//detail 페이지에 필요한 data를 ModelAndView에 저장
	public void getDetail(ModelAndView mView, int num);
	//영화 업데이트(request로 수정사항을 받아서 dto에 저장한 다음에 전송)
	public void update(MovieDto dto, HttpServletRequest request);
	//영화 삭제-> 번호를 매개로 해서 삭
	public void delete(int num);
	//영화 개추 -> 딱히 전달한 값은 없고 그냥 실행되면 카운트 1 -> 나중에 수정해야할지도 모
	public void thumsupCount();
	//영화 비추 -> 딱히 전달한 값은 없고 그냥 실행되면 카운트 1 -> 마찬가지로 나중에 수정할지
	public void thumsdownCount();
	
}
