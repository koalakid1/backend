package com.habit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.habit.domain.BoardDTO;
import com.habit.domain.BoardVO;
import com.habit.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

	private BoardMapper mapper;
	
	@Override
	public List<BoardDTO> getList() {
		log.info("get List.......");
		return mapper.getList();
	}

	@Override
	public BoardVO get(Long b_num) {
		log.info("get board with b_num : " + b_num);
		return mapper.read(b_num);
	}

	@Override
	public List<BoardDTO> getListMyBoard(Long m_num) {
		// TODO Auto-generated method stub
		return mapper.getListMyBoard(m_num);
	}

	@Override
	public List<BoardDTO> getListLikeBoard(Long m_num) {
		// TODO Auto-generated method stub
		return mapper.getListLikeBoard(m_num);
	}

}
