package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper mapper;
	
	//목록
//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.....");
//		
//		return mapper.getList();
//	}
	
	//목록 w/ paging
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getList w/ criteria : " + cri);
		return mapper.getListWithPaging(cri);
	}
	
	//전체글수
	@Override
	public int getTotalCount(Criteria cri) {
		log.info("get Total Count");
		return mapper.getTotalCount(cri);
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
	
	//수정하기
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify...." + board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("delete...." + bno);
		return mapper.delete(bno) == 1;
	}





}
