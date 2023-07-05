package com.kariyarn.personal.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.member.dto.MemberDto;

public interface MemberService {
	public void addMember(MemberDto dto);
	public void loginProcess(MemberDto dto, HttpSession session);
	public void getInfo(HttpSession session, ModelAndView mView);
	public void updateMemberPwd(HttpSession session, MemberDto dto, ModelAndView mView);
	public Map<String, Object> updateProfile(HttpServletRequest request, MultipartFile mFile);
	public void updateMember(MemberDto dto, HttpSession session);
	public void deleteMember(HttpSession session, ModelAndView mView);
}
