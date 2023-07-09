package com.kariyarn.personal.movie.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.movie.dto.MovieDto;
import com.kariyarn.personal.movie.dto.MovieReviewDto;
import com.kariyarn.personal.movie.service.MovieService;

@Controller
public class MovieController {

	@Autowired
	private MovieService service;
		
	//리뷰 수정 요청 처리(json)
	@RequestMapping("/movie/review_update")
	@ResponseBody
	public Map<String, Object> reviewUpdate(MovieReviewDto dto){
		service.updateReview(dto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	//리뷰 삭제
	@RequestMapping("/movie/review_delete")
	@ResponseBody
	public Map<String, Object> reviewDelete(HttpServletRequest request){
		service.deleteReview(request);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", true);
		return map;
	}
	//댓글 더보기
	@RequestMapping("/commu/ajax_review_list")
	public String reviewList(HttpServletRequest request) {
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		service.moreReview(request);
		return "movie/ajax_review_list";
	}
	//새로운 댓글 저장 요청 처리
	@RequestMapping("movie/review_insert")
	public String reviewInsert(HttpServletRequest request, int ref_group) {
		service.saveReview(request);
		return "redirect:/movie/detail?num="+ref_group;
	}
	
	//delete
	@RequestMapping("/movie/delete")
	public String delete(int num) {
		service.delete(num);
		return "redirect:/movie/list";
	}
	//update
	@RequestMapping("/movie/update")
	public String update(MovieDto dto, HttpServletRequest request) {
		service.update(dto, request);
		return "redirect:/movie/list";
		
	}
	
	//updateform 이동
	@RequestMapping("/movie/update_form")
	public ModelAndView uploadform(ModelAndView mView, int num) {
		service.getDetail(mView, num);
		mView.setViewName("movie/update_form");
		return mView;
	}
	
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
	
	//랭킹 리스트 이동
	@RequestMapping("/ranking/list")
	public String Rankinglist(HttpServletRequest request) {
		service.getRanking(request);
		return "ranking/list";
	}
	
	//메인 페이지로 이동
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		service.getRanking(request);
		return "home";
	}
	
	//업데이트 폼 이동, 업데이트, 삭제는 일단 나중에 하기로ㅇㅇ
}
