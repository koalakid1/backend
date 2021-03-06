package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyDTO;
import org.zerock.domain.ReplyVO;


public interface ReplyService {
	//등록
	public int register(ReplyVO vo);
	
	//조회
	public ReplyVO get(Long rno);
	
	//수정
	public int modify(ReplyVO vo);
	
	//삭제
	public int remove(Long rno);
	
	//조회 w/ paging 처리
	public List<ReplyVO> getList(Criteria cri, Long bno);
	
	public ReplyDTO getListPage(Criteria cri, Long bno);
}
