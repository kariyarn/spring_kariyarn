package com.example.boot06;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {
	
	@GetMapping("users/info")
	public String info(Model model) {
		//DB에서 읽어온 개인정보라고 가정
		String address = "서울시 강남구 삼원빌딩";
		/*
		 * 컨트롤러의 매개변수로 전달된 Model 객체에 addAttribute() 메소드를 이용해서
		 * view page 에 전달할 내용을 담으면 알아서 HttpServletRequest 객체에 담긴다.
		 * 또한 컨트롤러의 return type으로 리턴하지 않아도 동작한다.
		 */
		model.addAttribute("address", address);
		model.addAttribute("name", "바보바보바보야");
		return "users/info";
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		//invalidate()는 초기화구나
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/users/loginform")
	public String loginform() {
		return "users/loginform";
	}
	
	@PostMapping("/users/login")
	public String login(HttpSession session, String id) {
		//가상의 로그인 처리를 한다.
		//session.invalidate(); //초기화 후에 세션에 정보를 담을 수 없다.
		session.setAttribute("id", id);		
		return "redirect:/";
	}
}
