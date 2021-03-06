package com.habit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private Long b_num;
	private Long c_num;
	private Long m_num;
	private String b_title;
	private Date b_writeDate;
	private Date b_updateDate;
	private String b_content;
	private Long b_readCount;
	private Long b_likeCount;
	private Long b_replyCount;
}

