package com.kariyarn.personal.commu.service;

import javax.servlet.http.HttpServletRequest;

import com.kariyarn.personal.commu.dto.CommuCommentDto;
import com.kariyarn.personal.commu.dto.CommuDto;

public interface CommuService {
	//목록을 얻어오는 서비스
	public void getList(HttpServletRequest request);
	//게시물을 보는 서비스->왜 request?
	public void getDetail(HttpServletRequest request);
	//게시물을 저장하는 거라 dtoㅇㅇ
	public void saveContent(CommuDto dto);
	//게시물을 업데이트하는거니 당연히 dto
	public void updateContent(CommuDto dto);
	//게시글을 삭제하는 기능 id랑 비번 일치. id는 session에서 얻어오나봄
	public void deleteContent(int num, HttpServletRequest request);
	public void getData(HttpServletRequest request);

	//이 아래에 댓글 관련한 기능 만들기(나중에)
	public void saveComment(HttpServletRequest request);//댓글 저장
	public void deleteComment(HttpServletRequest request);//댓글 삭제
	public void updateComment(CommuCommentDto dto);//댓글 수정
	//댓글의 1페이지 내용만 먼저 보여주고...더 필요하다면 더 로딩시켜서 더 볼 수 있게 하기(ajax로)
	public void moreCommentList(HttpServletRequest request);//댓글 더보기 기능
}
