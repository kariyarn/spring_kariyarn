package com.example.boot05.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot05.member.dao.MemberDao;
import com.example.boot05.member.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDao dao;
	
	@GetMapping("/member/list")
	public ModelAndView list(ModelAndView mView) {
		
		//회원 목록
		List<MemberDto> list = dao.getList();
		mView.addObject("list", list);
		mView.setViewName("member/list");
		
		return mView;
	}
	
	@PostMapping("/member/insert")
	public ModelAndView insert(ModelAndView mView, HttpServletRequest request) {
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
		MemberDto dto = new MemberDto();
		dto.setName(name);
		dto.setAddr(addr);
		
		dao.insert(dto);
		mView.setViewName("redirect:/member/list");
		return mView;
	}
	
	@GetMapping("/member/insert_form")
	public ModelAndView insert_form() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("member/insert_form");
		return mView;
	}
	
	@GetMapping("/member/update_form")
	public String update_form(HttpServletRequest request) {
		String strNum = request.getParameter("num");
		int num = Integer.parseInt(strNum);
		MemberDto dto = dao.getData(num);
		request.setAttribute("dto", dto);

		return "member/update_form";
	}
	
	@PostMapping("/member/update")
	public ModelAndView update(ModelAndView mView, MemberDto dto) {
		dao.update(dto);
		mView.setViewName("redirect:/member/list");
		return mView;
	}
}
