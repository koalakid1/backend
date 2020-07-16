package com.habit.domain;

import lombok.Data;

@Data
public class MemberVO {
	
	private Long m_num;
	private String m_email;
	private String m_name;
	private String m_pw;
	private Long m_rank;
	private String m_intro;
	private Long m_count;
	
}
