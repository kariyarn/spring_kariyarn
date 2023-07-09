package com.kariyarn.personal.movie.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.NotEqualsOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.commu.dto.CommuCommentDto;
import com.kariyarn.personal.exception.DonEqualException;
import com.kariyarn.personal.exception.NotDeleteException;
import com.kariyarn.personal.movie.dao.MovieDao;
import com.kariyarn.personal.movie.dao.MovieReviewDao;
import com.kariyarn.personal.movie.dto.MovieDto;
import com.kariyarn.personal.movie.dto.MovieReviewDto;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieDao dao;
	
	@Autowired
	private MovieReviewDao reviewdao;

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
	      
	      //검색어 관련
		  //request 영역의 keyword랑 condition(?)을 얻어온다.
		  String keyword=request.getParameter("keyword");
		  String condition=request.getParameter("condition");
		  if(keyword==null) {
		  	keyword="";
		   	condition="";
		  }
		    
		  //한글을 검색창에 띄울 수 없으므로 ENcoder를 이용해서 적절하게 인코딩
		  String encodedK=URLEncoder.encode(keyword);
	      
	      //startRowNum 과 endRowNum  을 movieDto 객체에 담고
	      MovieDto dto = new MovieDto();
	      dto.setStartRowNum(startRowNum);
	      dto.setEndRowNum(endRowNum);
	      
	      
		    //만일 검색 키워드가 넘어온다면
		    if(!keyword.equals("")) {
		    	//검색 조건이 무엇인가에 따라 분기
		    	if(condition.equals("title_caption")) {//w제목+내용 검색인 경우
		    		dto.setTitle(keyword);
		    		dto.setCaption(keyword);
		    	}else if(condition.equals("title")) {
		    		dto.setTitle(keyword);
		    	}else if(condition.equals("writer")) {
		    		dto.setWriter(keyword);
		    	}
		    }
	      
	      //movieDao 객체를 이용해서 회원 목록을 얻어온다.
	      List<MovieDto> list = dao.getList(dto);
	      
	      //검색 키워드에 부합하는 전체 글의 갯수
	      int totalRow = dao.getCount(dto);
	      
	      //하단 시작 페이지 번호 
	      int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT) * PAGE_DISPLAY_COUNT;
	      //하단 끝 페이지 번호
	      int endPageNum = startPageNum + PAGE_DISPLAY_COUNT - 1;
	      
	      //전체 페이지의 갯수 구하기
	      int totalPageCount = (int)Math.ceil(totalRow / (double)PAGE_ROW_COUNT);
	      //끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	      if(endPageNum > totalPageCount){
	         endPageNum = totalPageCount; //보정해 준다. 
	      }
	      
	      //평균 평점을 보이게 하기 위한 처리
	      
	      //request 영역에 담아주기
	      request.setAttribute("list", list);   //movie list
	      request.setAttribute("startPageNum", startPageNum);   //시작 페이지 번호
	      request.setAttribute("endPageNum", endPageNum);   //끝 페이지 번호
	      request.setAttribute("pageNum", pageNum);   //현재 페이지 번호
	      request.setAttribute("totalPageCount", totalPageCount);   //모든 페이지 count
		  request.setAttribute("keyword", keyword);
		  request.setAttribute("encodedK", encodedK);
		  request.setAttribute("totalRow", totalRow); 
		  request.setAttribute("condition", condition);
		
	}

	@Override
	public void saveImage(MovieDto dto, HttpServletRequest request) {
		
	     //title이 같으면 exception발생시키기
		//getNum이 아직 부여되지 않아서 없음ㅇㅇ
		//getData를 하나 더 만들어야겠다
		//resultDto가 null이면 500발생한다(신규추가가 안됨)
		 MovieDto resultDto = dao.getData(dto.getTitle());
		 if(resultDto != null) {
			 String title = resultDto.getTitle();
			 if(title.equals(dto.getTitle())) {
			 throw new DonEqualException("같은 영화가 이미 존재합니다.");
		 }
		 }
		
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
	      //Movie 는 사진 다운 기능이 없다. -> orgFileName, saveFileName, fileSize 저장할 필요X
	      //imagePath 만 저장해주면 됨
	      dto.setImagePath("/resources/upload/" + saveFileName);
	      
	      //MovieDao 를 이용해서 DB 에 저장하기
	      dao.insert(dto);		
		
	}

	@Override
	public void getDetail(ModelAndView mView, int num) {
		//dao로 해당 게시글 num에 해당하는 데이터(dto)를 가져온다.
		MovieDto dto = dao.getData(num);
		//ModelAndView에 가져온 MovieDto를 담는다.
		
		/* 댓글에 관련한 처리 */
		final int PAGE_ROW_COUNT=10;
		int pageNum = 1;
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		int endRowNum = pageNum*PAGE_ROW_COUNT;
		
		MovieReviewDto reviewDto = new MovieReviewDto();
		reviewDto.setRef_group(num);
		reviewDto.setStartRowNum(startRowNum);
		reviewDto.setEndRowNum(endRowNum);
		
		
		
		List<MovieReviewDto> reviewList= reviewdao.getList(reviewDto);
		
		
		int totalRow = reviewdao.getCount(num);
		int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		
		if(totalRow>0) {
			// dto의 title을 매개로 평균 평점을 구한다.
			Double avgRate = reviewdao.getAvg(dto.getTitle());
			if (avgRate != null) {
			    // 평균 평점을 담는다.
			    mView.addObject("avgRate", avgRate);
			}
		}
		
		mView.addObject("dto", dto);
		mView.addObject("totalRow", totalRow);
		mView.addObject("reviewList", reviewList);
		mView.addObject("totalPageCount", totalPageCount);
		
	}

	@Override
	public void update(MovieDto dto, HttpServletRequest request) {
		 MovieDto resultDto = dao.getData(dto.getTitle());
		 if(resultDto != null) {
			 String title = resultDto.getTitle();
			 if(title.equals(dto.getTitle())) {
			 throw new DonEqualException("같은 영화가 이미 존재합니다.");
		 }
		 }
		
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
	    	 //기존 파일이 존재하면 삭제
	    	 File existingFile = new File(filePath + saveFileName);
	    	 if(existingFile.exists()) {
	    		 existingFile.delete();
	    	 }
	    	  
	         //upload 폴더에 파일을 저장한다.
	         image.transferTo(new File(filePath + saveFileName));
	         System.out.println();   //임시 출력
	      }catch(Exception e) {
	         e.printStackTrace();
	      }
	      
	      //dto 에 업로드된 파일의 정보를 담는다.
	      //-> parameer 로 넘어온 dto 에는 caption, image 가 들어 있었다.
	      //-> 추가할 것 : imagePath 만 추가로 담아주면 된다.
	      //-> num, title, caption : db 에 추가하면서 자동으로 들어감
	      //imagePath 만 저장해주면 됨
	      dto.setImagePath("/resources/upload/" + saveFileName);
	      
		dao.update(dto);
	}

	@Override
	public void delete(int num) {
		dao.delete(num);
	}

	@Override
	public void thumsupCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void thumsdownCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveReview(HttpServletRequest request) {
		//ref_group 파라미터 잘 가져왔나? dto.num을 통해 가져옴
		int ref_group=Integer.parseInt(request.getParameter("ref_group"));
		//review는 get Parameter로 가져왔다.
		String review = request.getParameter("review");
		//session 영역의 id를 이용해 가져옴
		String writer=(String)request.getSession().getAttribute("id");
		//title을 request
		String title=request.getParameter("title");
		//rate(input radio -> value)를 통해 가져옴
		String strRate=request.getParameter("rate");		
		int rate = 0;
		if(strRate==null) {
			rate = 0;	
		}else {
			rate=Integer.parseInt(strRate);
		}
		
		//seq-> 잘 가져옴
		int seq = reviewdao.getSequence();
		
		MovieReviewDto dto = new MovieReviewDto();
		//num
		dto.setNum(seq);
		//title-> 임시로 내가 지정
		dto.setTitle(title);
		//writer
		dto.setWriter(writer);
		//review
		dto.setReview(review);
		//rate
		dto.setRate(rate);
		//ref_group
		dto.setRef_group(ref_group);
		
		//동일작성 아이디검토
		MovieReviewDto resultDto = reviewdao.getEqual(dto);
		//만약 존재한다면
		if(resultDto != null) {
			//exception으로 던지
			throw new DonEqualException("한번만 리뷰 작성할 수 있습니다.");
		}else {
			//null이면 넣는다.
			reviewdao.insert(dto);	
		}
		
	}

	@Override
	public void deleteReview(HttpServletRequest request) {
		String strNum = request.getParameter("num");
		int num = Integer.parseInt(strNum);
		MovieReviewDto dto = reviewdao.getData(num);
		String id = (String)request.getSession().getAttribute("id");
		if(!dto.getWriter().equals(id)) {
			throw new NotDeleteException("타인의 리뷰는 삭제할 수 없습니다.");
		}
		reviewdao.delete(num);
	}

	@Override
	public void updateReview(MovieReviewDto dto) {
		reviewdao.update(dto);
	}

	@Override
	public void moreReview(HttpServletRequest request) {
		//로그인된 아이디
		String id =(String)request.getSession().getAttribute("id");
		//ajax 요청 파라미터로 넘어오는 댓글의 페이지 번호를 읽어낸다.
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		//ajax요청 파라미터로 넘어오는 원글의 글 번호를 읽어낸다.
		int num = Integer.parseInt(request.getParameter("num"));
		
	     /*
        	[ 댓글 페이징 처리에 관련된 로직 ]
	     */
	     //한 페이지에 몇개씩 표시할 것인지
	     final int PAGE_ROW_COUNT=10;
	
	     //보여줄 페이지의 시작 ROWNUM
	     int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	     //보여줄 페이지의 끝 ROWNUM
	     int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	     //원글의 글번호를 이용해서 해당글에 달린 댓글 목록을 얻어온다.
	     MovieReviewDto reviewDto=new MovieReviewDto();
	     reviewDto.setRef_group(num);
	     //1페이지에 해당하는 startRowNum 과 endRowNum 을 dto 에 담아서  
	     reviewDto.setStartRowNum(startRowNum);
	     reviewDto.setEndRowNum(endRowNum);
	
	     //pageNum에 해당하는 댓글 목록만 select 되도록 한다. 
	     List<MovieReviewDto> commentList=reviewdao.getList(reviewDto);
	     //원글의 글번호를 이용해서 댓글 전체의 갯수를 얻어낸다.
	     int totalRow=reviewdao.getCount(num);
	     //댓글 전체 페이지의 갯수
	     int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	
	     //view page 에 필요한 값 request 에 담아주기
	     request.setAttribute("commentList", commentList);
	     request.setAttribute("num", num); //원글의 글번호
	     request.setAttribute("pageNum", pageNum); //댓글의 페이지 번호
	}

	@Override
	public void avgRate(String title) {
		reviewdao.getAvg(title);
		
	}

	@Override
	public void getRanking(HttpServletRequest request) {

	      MovieDto dto = new MovieDto();
	      
	      //movieDao 객체를 이용해서 회원 목록을 얻어온다.
	      //ranking version
	      List<MovieDto> list = dao.getRanking(dto);
      
	      //request 영역에 담아주기
	      request.setAttribute("list", list);   //movie list
		
	}
}
