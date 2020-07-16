package com.habit.service;

import java.util.List;

import com.habit.domain.BoardVO;

public interface BoardService {
	public List<BoardVO> getList();
	
	public BoardVO get(Long b_num);
	
}
