package com.habit.domain;

import java.util.Date;

import lombok.Data;

@Data
public class HBoardVO {
	
	private Long b_num;
	private String b_title;
	private String b_content;
	private String b_writer;
	private Date b_writeDate;
	private Date b_updateDate;
	
}
