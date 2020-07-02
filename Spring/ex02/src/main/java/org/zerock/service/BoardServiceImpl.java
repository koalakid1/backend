package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	

	@Setter(onMethod_= @Autowired)
	private BoardAttachMapper attachMapper;
	
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
	@Transactional
	@Override
	public void register(BoardVO board) {
		mapper.insertSelectKey(board);
		log.info("register" + board.getAttachList());
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) {
			return;
		}
		board.getAttachList().forEach(attach ->{
			attach.setBno(board.getBno());
			
			attachMapper.insert(attach);
		});
	}
	

	//상세보기
	@Override
	public BoardVO get(Long bno) {
		log.info("get...." + bno);
		return mapper.read(bno);
	}
	
	//수정하기
	@Transactional
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify...." + board);
		
		attachMapper.deleteAll(board.getBno());
		
		boolean modifyResult = mapper.update(board) == 1;
		
		if(modifyResult && board.getAttachList() != null && board.getAttachList().size() > 0) {
			board.getAttachList().forEach(attach -> {
				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}
		
		return modifyResult;
	}
	
	@Transactional
	@Override
	public boolean remove(Long bno) {
		log.info("delete...." + bno);
		attachMapper.deleteAll(bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {
		log.info("get Attach list by bno" + bno);
		return attachMapper.findByBno(bno);
	}





}
