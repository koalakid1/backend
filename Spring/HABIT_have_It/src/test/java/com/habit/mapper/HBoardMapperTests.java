package com.habit.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.habit.domain.HBoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class HBoardMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private HBoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(hboard -> log.info(hboard));
	}
	
	@Test
	public void testInsert() {
		HBoardVO hboard = new HBoardVO();
		hboard.setB_title("새로작성한 글");
		hboard.setB_content("새로 작성하는 내용");
		hboard.setB_writer("newbie");
		
		mapper.insert(hboard);
		log.info(hboard);
	}
	
	@Test
	public void testInsertSelectKey() {
		HBoardVO hboard = new HBoardVO();
		hboard.setB_title("새로작성한 글 --ss");
		hboard.setB_content("새로 작성하는 내용 --ss");
		hboard.setB_writer("newbie");
		
		mapper.insertSelectKey(hboard);
		log.info(hboard);
	}
	
}
