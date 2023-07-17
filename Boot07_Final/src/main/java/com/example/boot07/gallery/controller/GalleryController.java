package com.example.boot07.gallery.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.gallery.dto.GalleryDto;
import com.example.boot07.gallery.service.GalleryService;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService service;
	
	@Value("${file.location}")
	private String fileLocation;

	// jpg, png, gif 이미지 데이터를 응답할수 있도록 produces 에 배열로 전달한다.
	@GetMapping(
		value = "gallery/images/{imageName}",
		produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE, 
				MediaType.IMAGE_GIF_VALUE }
	)
	@ResponseBody
	public byte[] galleryImage(@PathVariable("imageName") String imageName) throws IOException {
		// imageName 에는 응답해줄 이미지의 이름이 들어 있다.
		
		// 읽어들일 파일의 경로
		// C:/acorn202304/upload/kim1.png  형식의 경로 
		String absolutePath=fileLocation + File.separator + imageName;
		// 파일에서 읽어들일 InputStream 
		InputStream is=new FileInputStream(absolutePath);
		// fileLocation 필드에는 파일이 저장되어 있는 서버의 파일 시스템상에서의 위치가 들어 있다.
		return IOUtils.toByteArray(is);
	}
	
	//gallery 게시글의 num이 parameter get 방식으로 넘어온다.
	//detail 페이지
	@RequestMapping(value = "/gallery/detail", method = RequestMethod.GET)
	public ModelAndView detail(ModelAndView mView, int num) {
		//갤러리 detail 페이지에 필요한 data를 num으로 가져와, ModelAndView에 저장
		service.getDetail(mView, num);
		mView.setViewName("gallery/detail");
		return mView;
	}
	
	//gallery 사진 업로드 & DB저장
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/upload")
	public String upload(GalleryDto dto, HttpServletRequest request) {
		//form에서 dto를 데이터를 받아옴
		//dto : caption, MulipartFile image를 가지고 있다.
		//request : imagePath 만드는데 사용, session 영역으 id 가져오는데 사용
		service.saveImage(dto, request);
		return "gallery/upload";
	}
	
	//갤러리 업로드 form페이지로 이동
	@RequestMapping("/gallery/upload_form")
	public String upload_form(HttpServletRequest request) {

		return "gallery/upload_form";
	}
	//갤러리 업로드 form2페이지로 이동
	@RequestMapping("/gallery/upload_form2")
	public String upload_form2(HttpServletRequest request) {

		return "gallery/upload_form2";
	}
	
	//갤러리 업로드 form3페이지로 이동
	@RequestMapping("/gallery/upload_form3")
	public String upload_form3(HttpServletRequest request) {

		return "gallery/upload_form3";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/gallery/ajax_upload")
	@ResponseBody
	public Map<String, Object> ajaxUpload(GalleryDto dto, HttpServletRequest request){
		//서비스를 이용해서 업로드된 이미지를 저장하고
		service.saveImage(dto, request);
		//{"isSeccess":true} 형식의 json문자열 응답
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isSuccess", "true");		
		return map;
	}
	
	//갤러리 리스트 보이게 하기
	@RequestMapping("/gallery/list")
	public String list(HttpServletRequest request) {
		service.getList(request);
		return "gallery/list";
	}
}
