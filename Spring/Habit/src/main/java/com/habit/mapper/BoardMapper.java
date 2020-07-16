package com.habit.mapper;

import java.util.List;

import com.habit.domain.BoardVO;

public interface BoardMapper {
	public List<BoardVO> getList();
	
	public BoardVO read(Long b_num);
}
