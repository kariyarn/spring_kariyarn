package com.example.boot07.file.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.file.dto.FileDto;
import com.example.boot07.file.service.FileService;

@Controller
public class FileController {

	@Autowired
	private FileService service;
	
	  
	   @RequestMapping("/file/list")
	   public String list(HttpServletRequest request) {
	      
	      service.getList(request);
	      
	      return "file/list";
	   }
	   
	   @RequestMapping("/file/upload_form")
	   public String uploadForm() {
	      
	      return "file/upload_form";
	   }
	   
	   @RequestMapping("/file/upload")
	   public ModelAndView upload(FileDto dto, ModelAndView mView, HttpServletRequest request) {
		  //FileDto에는 폼전송되는 title과 myFile이 들어있다.
	      service.saveFile(dto, mView, request);
	      mView.setViewName("file/upload");
	      return mView;
	   }
	   
	   //다운로드해줄 파일의 번호가 요청 파라미터로 전달된다.
	   @RequestMapping("/file/download")
	   public ResponseEntity<InputStreamResource> download(int num) throws UnsupportedEncodingException, FileNotFoundException {
		  //다운로드해줄 파일의 번호를 서비스에 전달해서 ResponseEntity 객체를 얻어내서 리턴해준다.
	      return service.getFileData(num);
	   }
	   
	   @RequestMapping("/file/delete")
	   public ModelAndView delete(int num, ModelAndView mView, HttpServletRequest request) {
	      service.deleteFile(num, request);
	      mView.setViewName("redirect:/file/list");
	      return mView;
	   }
}