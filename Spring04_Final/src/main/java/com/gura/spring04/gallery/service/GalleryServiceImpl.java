package com.gura.spring04.gallery.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring04.gallery.dao.GalleryDao;
import com.gura.spring04.gallery.dto.GalleryDto;

@Service
public class GalleryServiceImpl implements GalleryService{
	
	@Autowired
	private GalleryDao dao;
	
/*
	@Override
	public void getList(HttpServletRequest request) {
		final int PAGE_ROW_COUNT=8;
		final int PAGE_DISPLAY_COUNT=5;
		
		int pageNum=1;
		
		String strPageNum=request.getParameter("pageNum");
	      if(strPageNum != null){
	         pageNum=Integer.parseInt(strPageNum);
	      }   
	      
	      int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	      int endRowNum=pageNum*PAGE_ROW_COUNT;
	         
	      GalleryDto dto=new GalleryDto();
	      dto.setStartRowNum(startRowNum);
	      dto.setEndRowNum(endRowNum);
	      
	      //파일 목록을 select 해 온다.(검색 키워드가 있는경우 키워드에 부합하는 전체 글) 
	      List<GalleryDto> list= gallerydao.getList(dto);
	      
	      //전체 글의 갯수(검색 키워드가 있는경우 키워드에 부합하는 전체 글의 갯수)
	      int totalRow= gallerydao.getCount(dto);
	      
	      //하단 시작 페이지 번호 
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호
	      int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	      
	      //전체 페이지의 갯수 구하기
	      int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum=totalPageCount; //보정해 준다. 
	      }
	      
	      //응답에 필요한 데이터를 view page 에 전달하기 위해  request scope 에 담는다
	      request.setAttribute("list", list);
	      request.setAttribute("pageNum", pageNum);
	      request.setAttribute("startPageNum", startPageNum);
	      request.setAttribute("endPageNum", endPageNum);
	      request.setAttribute("totalPageCount", totalPageCount);
		
	}

	@Override
	public void getImageData(int num, ModelAndView mView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteImage(int num, HttpServletRequest request) {
		//삭제할 파일의 정보 얻어오기
		GalleryDto dto = gallerydao.getData2(num);
		//글 작성자와 로그인된 아이디가 일치하는지 확인해서 일치하면 삭제하고, 일치하지 않으면 예외를 발생시키기
		String id=(String)request.getSession().getAttribute("id");
		if(!dto.getWriter().equals(id)) {
			//예외를 발생시키면 해당 예외를 처리하는 곳으로 실행의 흐름이 넘어간다.
			throw new NotDeleteException("남의 파일 지우지 않습니다.");
		}
		//정보 삭제
		gallerydao.delete(num);
	}

	@Override
	public Map<String, Object> saveImage(HttpServletRequest request, MultipartFile mFile) {
		//업로드된 파일에 대한 정보를 MultipartFile 객체를 이용해서 얻어낼수 있다.   
	      
	      //원본 파일명
	      String orgFileName=mFile.getOriginalFilename();
	      //upload 폴더에 저장할 파일명을 직접구성한다.
	      // 1234123424343xxx.jpg
	      String saveFileName=System.currentTimeMillis()+orgFileName;
	      
	      // webapp/upload 폴더까지의 실제 경로 얻어내기 
	      String realPath=request.getServletContext().getRealPath("/resources/upload");
	      // upload 폴더가 존재하지 않을경우 만들기 위한 File 객체 생성
	      File upload=new File(realPath);
	      if(!upload.exists()) {//만일 존재 하지 않으면
	         upload.mkdir(); //만들어준다.
	      }
	      try {
	         //파일을 저장할 전체 경로를 구성한다.  
	         String savePath=realPath+File.separator+saveFileName;
	         //임시폴더에 업로드된 파일을 원하는 파일을 저장할 경로에 전송한다.
	         mFile.transferTo(new File(savePath));
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      // json 문자열을 출력하기 위한 Map 객체 생성하고 정보 담기 
	      Map<String, Object> map=new HashMap<String, Object>();
	      map.put("imagePath", "/resources/upload/"+saveFileName);
	      
	      return map;
	}

	@Override
	public void saveImage(GalleryDto dto) {
		gallerydao.insert(dto);
		
	}

	@Override
	public void getDetail(HttpServletRequest request) {
		 //자세히 보여줄 글번호를 읽어온다. 
	      int num=Integer.parseInt(request.getParameter("num"));

	      //CafeDto 객체를 생성해서 
	      GalleryDto dto=new GalleryDto();
	      //자세히 보여줄 글번호를 넣어준다. 
	      dto.setNum(num);
	      
	      //글하나의 정보를 얻어온다.
	      GalleryDto resultDto=gallerydao.getData(dto);
	      
	      request.setAttribute("dto", resultDto);

	}

	@Override
	public void updateForm(int num, HttpServletRequest request) {
		GalleryDto dto =  gallerydao.getData2(num);
		request.setAttribute("dto", dto);
	}

	@Override
	public void updateImage(GalleryDto dto) {
		gallerydao.update(dto);		
	}
*/

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
	      
	      // webapp/upload 폴더 까지의 실제 경로(서버의 파일 시스템 상에서의 경로)
	      String realPath = request.getServletContext().getRealPath("/resources/upload");
	      //db 에 저장할 저장할 파일의 상세 경로
	      String filePath = realPath + File.separator;
	      //디렉토리를 만들 파일 객체 생성
	      File upload = new File(filePath);
	      if(!upload.exists()) {
	         //만약 디렉토리가 존재하지X
	         upload.mkdir();//폴더 생성
	      }
	      //저장할 파일의 이름을 구성한다. -> 우리가 직접 구성해줘야한다.
	      String saveFileName = System.currentTimeMillis() + orgFileName;
	      
	      try {
	         //upload 폴더에 파일을 저장한다.
	         image.transferTo(new File(filePath + saveFileName));
	         System.out.println();   //임시 출력
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      //dto 에 업로드된 파일의 정보를 담는다.
	      //-> parameer 로 넘어온 dto 에는 caption, image 가 들어 있었다.
	      //-> 추가할 것 : writer(id), imagePath 만 추가로 담아주면 된다.
	      //-> num, regdate : db 에 추가하면서 자동으로 들어감
	      String id = (String)request.getSession().getAttribute("id");
	      dto.setWriter(id);
	      //gallery 는 사진 다운 기능이 없다. -> orgFileName, saveFileName, fileSize 저장할 필요X
	      //imagePath 만 저장해주면 됨
	      dto.setImagePath("/resources/upload/" + saveFileName);
	      
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
