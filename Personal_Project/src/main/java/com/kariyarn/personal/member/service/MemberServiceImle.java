package com.kariyarn.personal.member.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.member.dao.MemberDao;
import com.kariyarn.personal.member.dto.MemberDto;

@Service
public class MemberServiceImle implements MemberService{
	
	@Autowired
	private MemberDao dao;
	
	//회원 한 명의 정보를 추가한다.
	@Override
	public void addMember(MemberDto dto) {
		BCryptPasswordEncoder encorder=new BCryptPasswordEncoder();
		String encodedPwd = encorder.encode(dto.getPwd());
		dto.setPwd(encodedPwd);
		dao.signup(dto);
	}

	@Override
	public void loginProcess(MemberDto dto, HttpSession session) {
		boolean isValid=false;
		MemberDto resultDto=dao.getData(dto.getId());
		if(resultDto!=null) {
			isValid = BCrypt.checkpw(dto.getPwd(), resultDto.getPwd());
		}
		if(isValid) {
			session.setAttribute("id", resultDto.getId());
		}
	}
	@Override
	public void getInfo(HttpSession session, ModelAndView mView) {
		String id=(String)session.getAttribute("id");
		MemberDto dto = dao.getData(id);
		mView.addObject("dto", dto);
	}

	@Override
	public Map<String, Object> updateProfile(HttpServletRequest request, MultipartFile mFile) {
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
	public void updateMemberPwd(HttpSession session, MemberDto dto, ModelAndView mView) {
		//세션 영역에서 로그인된 아이디 읽어오기
		String id=(String)session.getAttribute("id");
		//DB에 저장된 회원정보 읽어오기
		MemberDto resultDto = dao.getData(id);
		//DB에 저장된 암호화된 비밀번호
		String encodedPwd=resultDto.getPwd();
		//클라이언트가 입력한 비밀번호
		String inputPwd=dto.getPwd();
		//두 비밀번호가 일치하는지 확인
		boolean isValid=BCrypt.checkpw(inputPwd, encodedPwd);
		//만일 일치한다면
		if(isValid) {
			//새로운 비밀번호를 암호화한다.
			BCryptPasswordEncoder encorder=new BCryptPasswordEncoder();
			String encodedNewPwd=encorder.encode(dto.getNewPwd());
			//암호화된 비밀번호를 dto에 다시 넣어준다.
			dto.setNewPwd(encodedNewPwd);
			//dto에 로그인된 아이디도 넣어준다.
			dto.setId(id);
			//dao를 이요해서 DB에 수정 반영한다.
			dao.updatePwd(dto);
			//로그아웃처리
			session.removeAttribute("id");
		}
		 //작업의 성공여부를 ModelAndView 객체에 담아 놓는다(결국 HttpServletRequest 에 담긴다)
	      mView.addObject("isSuccess", isValid);
	      //로그인된 아이디도 담아준다.
	      mView.addObject("id", id);   
	}
	@Override
	public void updateMember(MemberDto dto, HttpSession session) {
		//로그인된 아이디를 읽어온다.
		String id=(String)session.getAttribute("id");
		//dto에 아이디도 담기
		dto.setId(id);
		//dao를 이용해서 수정반영
		dao.update(dto);	
		
	}

	@Override
	public void deleteMember(HttpSession session, ModelAndView mView) {
		String id = (String)session.getAttribute("id");
		dao.delete(id);
		session.removeAttribute("id");
		mView.addObject("id", id);
	}

}
