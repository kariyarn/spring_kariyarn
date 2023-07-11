package com.example.boot04;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@GetMapping("/")
	public String home(HttpServletRequest request) {
		
		List<String> noticeList = new ArrayList<>();
		noticeList.add("spring boot 시작합니다.");
		noticeList.add("어쩌구");
		noticeList.add("저쩌구");
		
		request.setAttribute("noticeList", noticeList);
		
		return "home";
	}
	
	@GetMapping("/fortune")
	public ModelAndView fortune(ModelAndView mView) {
		//오늘의 운세
		String fortune = "오늘은 좀 그렇네";
		//모델앤뷰 객체에 모델과 뷰페이지의 정보를 담고
		mView.addObject("fortune", fortune);
		mView.setViewName("fortune");
		//리턴
		return mView;
	}
	
	@GetMapping("/fortune2")
	public String fortune(HttpServletRequest request) {
		String fortune = "야호";
		request.setAttribute("fortune", fortune);
		return "fortune";
	}
}
