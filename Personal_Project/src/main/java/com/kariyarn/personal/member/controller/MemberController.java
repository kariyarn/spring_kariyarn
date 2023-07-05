package com.kariyarn.personal.member.controller;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.member.dto.MemberDto;
import com.kariyarn.personal.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//멤버 삭제
	@RequestMapping("/member/delete")
	public ModelAndView delete(HttpSession session, ModelAndView mView) {
		service.deleteMember(session, mView);
		mView.setViewName("member/delete");
		return mView;
	}
	
	//업데이트
	@RequestMapping(method = RequestMethod.POST, value = "member/update")
	public ModelAndView updateMember(HttpSession session, MemberDto dto, ModelAndView mView ) {
		service.updateMember(dto, session);
		mView.setViewName("redirect:/member/info");
		return mView;
	}
	
	//ajax 프로필 사진 업로드 요청처리
	@RequestMapping(method = RequestMethod.POST, value = "/member/profile_upload")
	@ResponseBody
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image){
		return service.updateProfile(request, image);
	}
	
	//업데이트 폼 요청
	@RequestMapping("member/updateform")
	public ModelAndView updateForm(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		mView.setViewName("member/updateform");
		return mView;
	}
	
	//비밀번호 수정 요청 처리
	@RequestMapping("/member/pwd_update")
	public ModelAndView pwdUpdate(MemberDto dto, ModelAndView mView, HttpSession session) {
		//서비스에 필요한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리한다.
		service.updateMemberPwd(session, dto, mView);
		//view page로 forward이동해서 작업 결과를 응답한다.
		mView.setViewName("redirect:/");
		return mView;
	}
	
	@RequestMapping("/member/pwd_updateform")
	public String pwdUpdateForm() {
		return "member/pwd_updateform";
	}
	
	//개인 정보 보기 요청 처리
	@RequestMapping("/member/info")
	public ModelAndView info(HttpSession session, ModelAndView mView) {
		service.getInfo(session, mView);
		mView.setViewName("member/info");
		return mView;
	}
	
	//로그아웃
	@RequestMapping("/member/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:/";
	}

	//로그인
	@RequestMapping("/member/login")
	public ModelAndView login(ModelAndView mView, MemberDto dto, String url, HttpSession session) {
		service.loginProcess(dto, session);
		
		String encodedUrl=URLEncoder.encode(url);
		mView.addObject("url", url);
		mView.addObject("encodedUrl", encodedUrl);
		
		mView.setViewName("member/login");
		return mView;
	}
	
	//로그인 폼 이동
	@RequestMapping("/member/loginform")
	public String loginform() {
		return "member/loginform";
	}
	
	//유저 한 명의 정보를 저장
	@RequestMapping(method = RequestMethod.POST, value = "/member/signup")
	public String signup(MemberDto dto) {
		service.addMember(dto);
		return "redirect:/";
	}

	@RequestMapping("/member/signup_form")
	public String signup_form() {
		return "member/signup_form";
	}
	
}
