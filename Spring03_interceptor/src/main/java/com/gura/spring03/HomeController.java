package com.gura.spring03;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	//프로젝트의 최상위 경로 요청이 오면
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		//응답에 필요한 데이터(Model)이라고 가정하자.
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("날씨가 많이 더워지고 있어요");
		noticeList.add("어쩌구");
		noticeList.add("저쩌구");
		//home.jsp 페이지에서 필요한 모델(data)를 httpServletRequest 객체에 담아두기
		request.setAttribute("noticeList", noticeList);
		
		return "home";
	}
	
	@RequestMapping("/play")
	public String play() {
		// /WEB-INF/views/play.jsp
		return "play";
	}
}
