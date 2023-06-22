package com.gura.spring02.member.service;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dto.MemberDto;

public interface MemberService {
	
	public void insert(MemberDto dto);
	public void update(MemberDto dto);
	public void delete(int num);
	public void getMemberInfo(ModelAndView mView, int num);
	public void getMemberList(ModelAndView mView);
}
