package com.habit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.habit.domain.HBoardVO;

public interface HBoardMapper {
	
//	@Select("select * from hBoard where b_num > 0")
	public List<HBoardVO> getList();
	
	public void insert(HBoardVO hboard);
	
	public void insertSelectKey(HBoardVO hboard);
	
}
