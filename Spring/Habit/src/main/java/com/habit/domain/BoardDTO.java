package com.habit.domain;

import java.util.Date;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class BoardDTO {
	
	private Long b_num;
	private Long c_num;
	private String c_name;
	private String m_name;
	private String b_title;
	private String b_content;
	private Date b_writeDate;
	private Long b_readCount;
	private Long b_likeCount;
	private Long b_replyCount;
	
}
