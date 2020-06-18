package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper mapper;
	
	//목록
	@Override
	public List<BoardVO> getList() {
		log.info("getList.....");
		
		return mapper.getList();
	}
	
	//등록
	@Override
	public void register(BoardVO board) {
		log.info("register : " + board);
		
		mapper.insertSelectKey(board);
	}
	

	//상세보기
	@Override
	public BoardVO get(Long bno) {
		log.info("get...." + bno);
		return mapper.read(bno);
	}


}
