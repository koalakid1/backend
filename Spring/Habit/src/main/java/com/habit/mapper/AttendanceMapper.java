package com.habit.mapper;

import java.util.List;

import com.habit.domain.AttendanceDTO;
import com.habit.domain.AttendanceVO;

public interface AttendanceMapper {
	public List<AttendanceVO> getList(Long m_num);
	
	public List<AttendanceDTO> getListWithCategory(Long m_num);
}
