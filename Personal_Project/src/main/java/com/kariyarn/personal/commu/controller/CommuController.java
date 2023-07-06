package com.kariyarn.personal.commu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kariyarn.personal.commu.dto.CommuCommentDto;
import com.kariyarn.personal.commu.dto.CommuDto;
import com.kariyarn.personal.commu.service.CommuService;

@Controller
public class CommuController {
	@Autowired
	private CommuService service;
	
	//댓글 수정 요청 처리(JSON을 응답하도록 한다)
		@RequestMapping("/commu/comment_update")
		@ResponseBody
		public Map<String, Object> commentUpdate(CommuCommentDto dto){
			service.updateComment(dto);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSuccess", true);
			//{"isSeccess":true} 형식의 JSON 문자열이 응답되도록 한다.
			return map;
		}
	
		//댓글 삭제 요청 처리
		@RequestMapping("/commu/comment_delete")
		@ResponseBody
		public Map<String, Object> commentDelete(HttpServletRequest request){
			service.deleteComment(request);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isSuccess", true);
			//{"isSuccess":true} 형식의 JSON문자열이 응답되도록 한다.
			return map;
		}
	

		//댓글 더보기 요청 처리
		@RequestMapping("/commu/ajax_comment_list")
		public String commentList(HttpServletRequest request) {
			//테스트를 위해 시간 지연 시키기
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			service.moreCommentList(request);
			
			return "commu/ajax_comment_list";
		}
	
		//새로운 댓글의 저장 요청 처리
		@RequestMapping("/commu/comment_insert")
		public String commentInsert(HttpServletRequest request, int ref_group) {
			//새로운 댓글을 저장하는 로직을 수행한다.
			service.saveComment(request);
			//ref-group은 원글의 글번호이기 때문에 원글 자세히 보기로 다시 리다일렉트 이동한다.
			return "redirect:/commu/detail?num="+ref_group;
		}
	
	   @RequestMapping("/commu/list")
	   public String list(HttpServletRequest request) {
		   //서비스에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
	      service.getList(request);
	      //view page로 forward이동해서 응답하기
	      return "commu/list";
	   }
	   
	   @RequestMapping("/commu/insertform")
	   public String insertform() {
	      return "commu/insertform";
	   }
	   
	   @RequestMapping("/commu/insert")
	   public String insert(CommuDto dto, HttpSession session) {
	      //글 작성자는 세션에서 얻어낸다.
	      String writer=(String)session.getAttribute("id");
	      //dto 는 글의 제목과 내용만 있으므로 글작성자는 직접 넣어준다.
	      dto.setWriter(writer);
	      //서비스를 이용해서 새글을 저장한다.
	      service.saveContent(dto);
	      return "commu/insert";
	   }
	   
	   @RequestMapping("/commu/detail")
	   public String detail(HttpServletRequest request) {
		   //서비스에 HttpServletRequest 객체를 전달해서 응답에 필요한 데이터가 담기도록 하고
	      service.getDetail(request);
	      //view페이지로 forward 이동해서 응답.
	      return "commu/detail";
	   }
	   
	   @RequestMapping("/commu/delete")
	   public String delete(int num, HttpServletRequest request) {
		//서비스에 삭제할 글번호와 HttpServletRequest객체를 전달해서 해당 글을 삭제시키고
	      service.deleteContent(num, request);
	      //글 목록보기로 리다일렉트
	      return "redirect:/commu/list";
	   }
	   
	   @RequestMapping("/commu/updateform")
	   public String updateForm(HttpServletRequest request) {
	      service.getData(request);
	      return "commu/updateform";
	   }
	   
	   @RequestMapping("/commu/update")
	   public String update(CommuDto dto) {
	      service.updateContent(dto);
	      return "commu/update";
	   }
}
