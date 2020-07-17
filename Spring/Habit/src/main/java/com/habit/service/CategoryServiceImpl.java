package com.habit.service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import com.habit.domain.CategoryVO;
import com.habit.mapper.CategoryMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private CategoryMapper mapper;
	
	@Override
	public List<CategoryVO> getCategory() {
		// TODO Auto-generated method stub
		return mapper.getCategory();
	}

}
