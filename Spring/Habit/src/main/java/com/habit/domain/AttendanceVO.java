package com.habit.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AttendanceVO {
	private Long m_num;
	private Long c_num;
	private String b_writeDate;
	
	private List<MemberVO> memberList;
	private List<CategoryVO> categoryList;
	
}
