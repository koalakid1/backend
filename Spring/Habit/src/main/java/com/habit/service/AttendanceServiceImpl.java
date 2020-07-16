package com.habit.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.habit.domain.AttendanceDTO;
import com.habit.domain.AttendanceVO;
import com.habit.mapper.AttendanceMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
	
	private AttendanceMapper mapper;
	
	@Override
	public List<AttendanceVO> read(Long m_num) {
		log.info("......");
		return mapper.getList(m_num);
	}

	@Override
	public List<AttendanceDTO> readWithCategory(Long m_num) {
		log.info("category를 포함해서 " + m_num +"의 월 출석일자를 얻어온다.");
		return mapper.getListWithCategory(m_num);
	}
	
}
