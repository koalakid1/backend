package com.habit.mapper;

import java.util.List;

import com.habit.domain.BoardDTO;
import com.habit.domain.BoardVO;

public interface BoardMapper {
	public List<BoardDTO> getList();
	
	public BoardVO read(Long b_num);

	public List<BoardDTO> getListMyBoard(Long m_num);

	public List<BoardDTO> getListLikeBoard(Long m_num);
	
}
