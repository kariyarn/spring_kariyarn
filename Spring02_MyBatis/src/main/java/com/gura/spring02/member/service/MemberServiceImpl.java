package com.gura.spring02.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.gura.spring02.member.dao.MemberDao;
import com.gura.spring02.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao dao;
	
	@Override
	public void insert(MemberDto dto) {
		dao.insert(dto);
	}

	@Override
	public void update(MemberDto dto) {
		dao.update(dto);
	}

	@Override
	public void delete(int num) {
		dao.delete(num);
	}

	@Override
	public void getMemberInfo(ModelAndView mView, int num) {
		MemberDto dto = dao.getData(num);
		mView.addObject("dto", dto);
	}

	@Override
	public void getMemberList(ModelAndView mView) {
		List<MemberDto> list = dao.getList();
		mView.addObject("list", list);
	}

}
