package com.example.boot07.gallery.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.boot07.gallery.dao.GalleryDao;
import com.example.boot07.gallery.dto.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryDao dao;
	
	//파일 저장 경로
	@Value("${file.location}")
	private String fileLocation;
	
	@Override
	public void getList(HttpServletRequest request) {
		  //한 페이지에 몇개씩 표시할 것인지
	      final int PAGE_ROW_COUNT=8;
	      //하단 페이지를 몇개씩 표시할 것인지
	      final int PAGE_DISPLAY_COUNT=5;
	      
	      //보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	      int pageNum=1;
	      //페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	      String strPageNum = request.getParameter("pageNum");
	      //만일 페이지 번호가 파라미터로 넘어 온다면
	      if(strPageNum != null){
	         //숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
	         pageNum=Integer.parseInt(strPageNum);
	      }
	      
	      //보여줄 페이지의 시작 ROWNUM
	      int startRowNum = 1 + (pageNum-1) * PAGE_ROW_COUNT;
	      //보여줄 페이지의 끝 ROWNUM
	      int endRowNum = pageNum * PAGE_ROW_COUNT;
	      
	      //startRowNum 과 endRowNum  을 GalleryDto 객체에 담고
	      GalleryDto dto = new GalleryDto();
	      dto.setStartRowNum(startRowNum);
	      dto.setEndRowNum(endRowNum);
	      
	      //GalleryDao 객체를 이용해서 회원 목록을 얻어온다.
	      List<GalleryDto> list = dao.getList(dto);
	      
	      //하단 시작 페이지 번호 
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호
	      int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	      
	      //전체 row 의 갯수
	      int totalRow = dao.getCount();
	      //전체 페이지의 갯수 구하기
	      int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum = totalPageCount; //보정해 준다. 
	      }
	      
	      //request 영역에 담아주기
	      request.setAttribute("list", list);   //gallery list
	      request.setAttribute("startPageNum", startPageNum);   //시작 페이지 번호
	      request.setAttribute("endPageNum", endPageNum);   //끝 페이지 번호
	      request.setAttribute("pageNum", pageNum);   //현재 페이지 번호
	      request.setAttribute("totalPageCount", totalPageCount);   //모든 페이지 count
		
	}

	@Override
	public void saveImage(GalleryDto dto, HttpServletRequest request) {
	      //업로드된 파일의 정보를 가지고 있는 MultipartFile 객체의 참조값을 얻어오기
	      MultipartFile image = dto.getImage();
	      //원본 파일명 -> 저장할 파일 이름 만들기위해서 사용됨
	      String orgFileName = image.getOriginalFilename();
	      //파일 크기 -> 다운로드가 없으므로, 여기서는 필요 없다.
	      long fileSize = image.getSize();
	      
	      //저장할 파일명 uuid문자열 + 원본 파일명
	      //원본파일명을 붙인 이유. 확장자가 다양하기 때문에
	      String saveFileName = UUID.randomUUID().toString()+orgFileName;
	      
	      //db 에 저장할 저장할 파일의 상세 경로
	      String filePath = fileLocation+File.separator+saveFileName;
	      //디렉토리를 만들 파일 객체 생성
	      File upload = new File(fileLocation);
	      if(!upload.exists()) {
	         //만약 디렉토리가 존재하지X
	         upload.mkdir();//폴더 생성
	      }
	      
	      try {
	         //upload 폴더에 파일을 저장한다.
	         image.transferTo(new File(filePath));
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	 
	      String id = (String)request.getSession().getAttribute("id");
	      dto.setWriter(id);
	      //gallery는 사진 저장 기능이 없어서 orgName, saveName, fileSize가 굳이 필요없다.
	      //DB에는 saveFileName만 저장하고 출력할 때 자세한 경로를 출력해 준다.
	      dto.setImagePath(saveFileName);
	      //GalleryDao 를 이용해서 DB 에 저장하기
	      dao.insert(dto);		
	}

	@Override
	public void getDetail(ModelAndView mView, int num) {
		//dao로 해당 게시글 num에 해당하는 데이터(dto)를 가져온다.
		GalleryDto dto = dao.getData(num);
		//ModelAndView에 가져온 galleryDto를 담는다.
		mView.addObject("dto", dto);
	}
}
