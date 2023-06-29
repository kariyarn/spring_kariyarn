package com.gura.spring04.cafe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.cafe.dto.CafeDto;
import com.gura.spring04.cafe.service.CafeService;

@Controller
public class CafeController {
	@Autowired
	private CafeService service;
	
	   @RequestMapping("/cafe/list")
	   public String list(HttpServletRequest request) {
		   //서비스에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
	      service.getList(request);
	      //view page로 forward이동해서 응답하기
	      return "cafe/list";
	   }
	   
	   @RequestMapping("/cafe/insertform")
	   public String insertform() {
	      return "cafe/insertform";
	   }
	   
	   @RequestMapping("/cafe/insert")
	   public String insert(CafeDto dto, HttpSession session) {
	      //글 작성자는 세션에서 얻어낸다.
	      String writer=(String)session.getAttribute("id");
	      //dto 는 글의 제목과 내용만 있으므로 글작성자는 직접 넣어준다.
	      dto.setWriter(writer);
	      //서비스를 이용해서 새글을 저장한다.
	      service.saveContent(dto);
	      return "cafe/insert";
	   }
	   
	   @RequestMapping("/cafe/detail")
	   public String detail(HttpServletRequest request) {
		   //서비스에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
	      service.getDetail(request);
	      //view페이지로 forward 이동해서 응답.
	      return "cafe/detail";
	   }
	   
	   @RequestMapping("/cafe/delete")
	   public String delete(int num, HttpServletRequest request) {
		//서비스에 삭제할 글번호와 HttpServletRequest객체를 전달해서 해당 글을 삭제시키고
	      service.deleteContent(num, request);
	      //글 목록보기로 리다일렉트
	      return "redirect:/cafe/list";
	   }
	   
	   @RequestMapping("/cafe/updateform")
	   public String updateForm(HttpServletRequest request) {
	      service.getData(request);
	      return "cafe/updateform";
	   }
	   
	   @RequestMapping("/cafe/update")
	   public String update(CafeDto dto) {
	      service.updateContent(dto);
	      return "cafe/update";
	   }
}
