package com.acorn.test;

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
	@RequestMapping("/")
	public String home(HttpServletRequest request) {
		List<String> noticeList = new ArrayList<String>();
		noticeList.add("날씨가 많이 더워지고 있어요");
		noticeList.add("어쩌구");
		noticeList.add("저쩌구");
		request.setAttribute("noticeList", noticeList);
		return "home";
	}
}
