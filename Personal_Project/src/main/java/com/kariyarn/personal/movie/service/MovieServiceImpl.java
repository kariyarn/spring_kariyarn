package com.kariyarn.personal.movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.kariyarn.personal.movie.dao.MovieDao;
import com.kariyarn.personal.movie.dto.MovieDto;

@Service
public class MovieServiceImpl implements MovieService{
	
	@Autowired
	private MovieDao dao;

	@Override
	public void getList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveImage(MovieDto dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDetail(ModelAndView mView, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MovieDto dto, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void thumsupCount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void thumsdownCount() {
		// TODO Auto-generated method stub
		
	}
	
	

}
