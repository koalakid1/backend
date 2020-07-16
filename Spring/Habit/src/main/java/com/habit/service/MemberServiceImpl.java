package com.habit.service;

import org.springframework.stereotype.Service;

import com.habit.domain.MemberVO;
import com.habit.mapper.MemberMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private MemberMapper mapper;
	
	@Override
	public MemberVO readMember(Long m_num) {
		// TODO Auto-generated method stub
		return mapper.getMember(m_num);
	}
	
}
