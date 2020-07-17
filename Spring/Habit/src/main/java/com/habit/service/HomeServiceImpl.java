package com.habit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.habit.domain.BoardDTO;
import com.habit.mapper.HomeMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService{

	private HomeMapper mapper;
	
	@Override
	public List<BoardDTO> getListBestBoard() {
		log.info("get List.......");
		return mapper.getListBestBoard();
	}

}
