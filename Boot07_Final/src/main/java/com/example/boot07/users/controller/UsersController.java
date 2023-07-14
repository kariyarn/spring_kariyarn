package com.example.boot07.users.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.boot07.users.dto.UsersDto;
import com.example.boot07.users.service.UsersService;

@Controller
public class UsersController {
	//의존객체 주입받기(DI)
	@Autowired
	private UsersService service;
	/*
	 * GET 방식 /users/signup_form 요청을 처리할 메소드
	 * - 요청방식이 다르면 실행되지 않는다.
	 */
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping(
				value="/users/images/{imageName}",
				produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE}
			)
	@ResponseBody
	public byte[] getImage(@PathVariable("imageName") String imageName) throws IOException {
		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.
		
		// 읽어들일 파일의 경로
		// C:/acorn202304/upload/kim1.png  형식의 경로 
		String absolutePath=fileLocation + File.separator + imageName;
		// 파일에서 읽어들일 InputStream 
		InputStream is=new FileInputStream(absolutePath);
		// 이미지 데이터(byte)를 읽어서 배열에 담아서 클라이언트에게 응답한다.
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping("/users/delete")
	public String delete(HttpSession session, Model model) {
		/*
		 * 컨트롤러의 메소드로 전달 받은 Model 객체를 서비스 객체에 전달해서
		 * view page에 전달할 data(모델) 이 담기도록 해야한다.
		 * Model 객체에 addAttribute()해서 담은 data는 Spring프레임워크가
		 * HttpServletRequest 객체에 setAttribute()해서 대신 담아준다.
		 * 따라서 forward이동된(응답을 위임받은)jsp페이지에서 해당 data를
		 * 사용할 수 있는 것이다.
		 */
		service.deleteUser(session, model);
		//view page의 정보를 리턴한다(forward 이동될 jsp 페이지의 위치)
		return "users/delete";
	}
	
	@PostMapping(value = "users/update")
	public String updateUser(HttpSession session, UsersDto dto, Model model) {
		//서비스를 이용해서 개인정보를 수정하고
		service.updateUser(dto, session);
		//개인정보 보기로 리다일렉트 이동시킨다.
		return ("redirect:/users/info");
	}
	
	//ajax 프로필 사진 업로드 요청처리
	@PostMapping(value = "/users/profile_upload")
	@ResponseBody
	public Map<String, Object> profileUpload(HttpServletRequest request, MultipartFile image){
		//서비스를 이용해서 이미지를 upload 폴더에 저장하고 리턴되는 Map을 리턴해서 json문자열 응답하기
		return service.saveProfileImage(request, image);
	}
	
	//업데이트 폼 요청
	@GetMapping("/users/updateform")
	public String updateForm(HttpSession session, Model model) {
		service.getInfo(session, model);
		return "users/updateform";
	}
	
	//비밀번호 수정 요청 처리
	@PostMapping("/users/pwd_update")
	public String pwdUpdate(UsersDto dto, Model model, HttpSession session) {
		//서비스에 필요한 객체의 참조값을 전달해서 비밀번호 수정 로직을 처리한다.
		service.updateUserPwd(session, dto, model);
		//view page로 forward이동해서 작업 결과를 응답한다.
		return "users/pwd_update";
	}
	
	@GetMapping("/users/pwd_updateform")
	public String pwdUpdateForm() {
		return "users/pwd_updateform";
	}
	
	//개인 정보 보기 요청 처리
	@GetMapping("/users/info")
	public String info(HttpSession session, Model model) {

		service.getInfo(session, model);
		
		return "users/info";
	}
	
	@GetMapping("/users/logout")
	public String logout(HttpSession session) {
		//세션에서 id라는 키값으로 저장된 값 삭제
		session.removeAttribute("id");
		return "users/logout";
	}
	
	//로그인 요청 처리하는 메소드
	@PostMapping("/users/login")
	public String login(Model model, UsersDto dto, String url, HttpSession session) {
		/*
		 * 서비스에서 비즈니스 로직을 처리할 때 필요로 하는 객체를 컨트롤러에서 직접 전달을 해 주어야 한다.
		 * 주로, HttpServletRequest, HttpServletResponse, HttpSession, MedelAndView
		 * 등등의 객체이다.
		 */
		service.loginProcess(dto, session);
		
		//로그인 후에 가야할 목적지 정보를 인코딩 하지 않는 것과 인코딩 한 것을 모두 Model 객체에 담고
		String encodedUrl=URLEncoder.encode(url);
		model.addAttribute("url", url);
		model.addAttribute("encodedUrl", encodedUrl);
		
		//view page로 forward 이동해서 응답한다.
		return "users/login";
	}
	
	//로그인 폼 요청 처리
	@GetMapping(value = "/users/loginform")
	public String loginform() {
		
		return "users/loginform";
	}
	
	@GetMapping(value = "/users/signup_form")
	public String signupForm() {
		return "users/signup_form";
	}
	
	@PostMapping("/users/signup")
	public String signup(UsersDto dto) {
		//서비스를 이용해서 DB에 저장하고
		service.addUser(dto);
		//view page로 forward이동해서 응답
		return "users/signup";
	}
}
