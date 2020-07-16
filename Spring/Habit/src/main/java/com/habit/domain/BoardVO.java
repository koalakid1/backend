package com.habit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long b_num;
	private String b_title;
	private Long m_num;
	private Date b_writeDate;
	private Date b_updateDate;
	private String b_content;
	private Long b_category;
	private Long b_readCount;
	private Long b_likeCount;
}

