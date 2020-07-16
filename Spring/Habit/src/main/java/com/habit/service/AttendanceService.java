package com.habit.service;

import java.util.List;

import com.habit.domain.AttendanceDTO;
import com.habit.domain.AttendanceVO;

public interface AttendanceService {
	public List<AttendanceVO> read(Long m_num);
	
	public List<AttendanceDTO> readWithCategory(Long m_num);
}
