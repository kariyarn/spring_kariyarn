package com.gura.spring04.gallery.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto;
import com.gura.spring04.gallery.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	//gallery 게시글의 num이 parameter get 방식으로 넘어온다.
	//detail 페이지
	@RequestMapping(value = "/gallery/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mView, int num) {
		//갤러리 detail 페이지에 필요한 data를 num으로 가져와, ModelAndView에 저장
		service.getDetail(mView, num);
		mView.setViewName("gallery/detail");
		return mView;
	}
	
	//gallery 사진 업로드 & DB저장
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		//form에서 dto를 데이터를 받아옴
		//dto : caption, MulipartFile image를 가지고 있다.
		//request : imagePath 만드는데 사용, session 영역으 id 가져오는데 사용
		service.saveImage(dto, request);
		return "gallery/upload";
	}
	
	//갤러리 업로드 form페이지로 이동
	@RequestMapping("/gallery/upload_form")
	public String upload_form(HttpServletRequest request) {

		return "gallery/upload_form";
	}
	//갤러리 업로드 form2페이지로 이동
	@RequestMapping("/gallery/upload_form2")
	public String upload_form2(HttpServletRequest request) {

		return "gallery/upload_form2";
	}
	
	//갤러리 업로드 form3페이지로 이동
	@RequestMapping("/gallery/upload_form3")
	public String upload_form3(HttpServletRequest request) {

		return "gallery/upload_form3";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고
		service.saveImage(dto, request);
		//{"isSeccess":true} 형식의 json문자열 응답
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", "true");		
		return map;
	}
	
	//갤러리 리스트 보이게 하기
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "gallery/list";
	}

	/*
	//업데이트
	@RequestMapping("/gallery/update")
	public String update(GalleryDto dto) {
		//작성자, 캡션, 이미지 경로를 담은 dto를 service로 보내서 작업 실행
		service.updateImage(dto);
		return "redirect:/gallery/list";
	}
	
	//업데이트 폼
	@RequestMapping("/gallery/update_form")
	public String updateForm(int num, HttpServletRequest reqeust) {
		service.updateForm(num, reqeust);
		return "gallery/update_form";
	}
	
	
	//게시글 삭제하기
	@RequestMapping("/gallery/delete")
	public String delete(int num, HttpServletRequest request) {
		service.deleteImage(num, request);
		return "redirect:/gallery/list";
	}
	
	//디테일 맵핑
	@RequestMapping("/gallery/detail")
	public String detail(HttpServletRequest request) {
		service.getDetail(request);
		return "gallery/detail";
	}
	
	//갤러리 리스트 보이게 하기
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "gallery/list";
	}
	
	//갤러리 업로드
	@RequestMapping("/gallery/upload")
	public String upload(GalleryDto dto, HttpSession session) {
		//아이디로 작성자 얻어내고
		String writer = (String)session.getAttribute("id");
		//dto에 담아둔다
		dto.setWriter(writer);
		//작성자, 캡션, 이미지 경로를 담은 dto를 service로 보내서 작업 실행
		service.saveImage(dto);
		return "redirect:/gallery/list";
	}
	
	//ajax 프로필 사진 업로드 요청 처리
	@RequestMapping(value = "/gallery/upload_image", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> upload_image(HttpServletRequest request, MultipartFile image){
		//서비스를 이용해서 이미지를 upload 폴더에 저장하고 리턴되는 Map을 리턴해서 json 응답하기
		return service.saveImage(request, image);
	}
	
	*/
}
