package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {
	private String title;
//	@DateTimeFormat(pattern="yyyy/MM/dd") 이런식으로 데이터 타입을 설정할 수 있지만 모든 패턴을 계속 만들어줘야하기 때문에 불편함
	private Date dueDate;
}
