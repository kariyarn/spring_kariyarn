package com.kariyarn.personal.movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.movie.dto.MovieDto;
import com.kariyarn.personal.movie.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService service;
	
	//detail 페이지
	@RequestMapping(method =RequestMethod.GET , value = "/movie/detail")
	public ModelAndView detail(ModelAndView mView, int num) {
		service.getDetail(mView, num);
		mView.setViewName("movie/detail");
		return mView;
	}
	
	//사진 업로드 & DB 저장
	@RequestMapping(method = RequestMethod.POST, value = "/movie/upload")
	public String upload(MovieDto dto, HttpServletRequest request) {
		service.saveImage(dto, request);
		return "redirect:/movie/list";
	}
	
	//갤러리 form 페이지 이동
	@RequestMapping("/movie/upload_form")
	public String upload_form(HttpServletRequest request) {
		return "movie/upload_form";
	}
	
	//갤러리 리스트 이동
	@RequestMapping("/movie/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "movie/list";
	}
	
	//업데이트 폼 이동, 업데이트, 삭제는 일단 나중에 하기로ㅇㅇ
}
