package com.gura.spring04.gallery.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dto.GalleryDto;

public interface GalleryService {
/*
	//목록 얻어오기
	public void getList(HttpServletRequest request);
	//업로드된 사진 저장하기
	public void saveImage(GalleryDto dto);
	//업데이트
	public void updateImage(GalleryDto dto);
	//파일 하나의 정보 얻어오기
	public void getImageData(int num, ModelAndView mView);
	//파일 디테일
	public void getDetail(HttpServletRequest request);
	//파일 삭제하기
	public void deleteImage(int num, HttpServletRequest request);
	//ajax 이미지 저장
	public Map<String, Object> saveImage(HttpServletRequest request, 
	         MultipartFile mFile);
	//업데이트
	public void updateForm(int num, HttpServletRequest request);
*/
	//갤러리의 list가져오기
	public void getList(HttpServletRequest request);
	//갤러리의 사진 upload*DB 저장하기
	public void saveImage(GalleryDto dto, HttpServletRequest request);
	//갤러리 detail 페이지에 필요한 data를 ModelAndView에 저장
	public void getDetail(ModelAndView mView, int num);
}
