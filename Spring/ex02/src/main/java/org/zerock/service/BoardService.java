package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	//목록
//	public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri); 
	//전체글수
	public int getTotalCount(Criteria cri);
	//등록
	public void register(BoardVO board);
	//상세보기
	public BoardVO get(Long bno);
	//수정
	public boolean modify(BoardVO board);
	//삭제
	public boolean remove(Long bno);
	
	public List<BoardAttachVO> getAttachList(Long bno);
}
