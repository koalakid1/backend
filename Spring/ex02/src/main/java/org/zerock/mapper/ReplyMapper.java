package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {
	//등록
	public int insert(ReplyVO vo);
	
	//조회
	public ReplyVO read(Long rno);
	
	//삭제
	public int delete(Long rno);
	
	//수정
	public int update(ReplyVO vo);
	
	//조회 w/ 페이징 처리
	public List<ReplyVO> getListWithPaging(
		@Param("cri") Criteria cri,
		@Param("bno") Long bno);
	
	public int getCountByBno(Long bno);
}
