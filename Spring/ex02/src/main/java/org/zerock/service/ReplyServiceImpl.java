package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j; 

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService {
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	//등록
	@Override
	public int register(ReplyVO vo) {
		log.info("register......" + vo);
		return mapper.insert(vo);
	}
	
	//조회
	@Override
	public ReplyVO get(Long rno) {
		log.info("get......" + rno);
		return mapper.read(rno);
	}
	
	//수정
	@Override
	public int modify(ReplyVO vo) {
		log.info("modify......" + vo);
		return mapper.update(vo);
	}

	//삭제
	@Override
	public int remove(Long rno) {
		log.info("remove......" + rno);
		return mapper.delete(rno);
	}

	//조회 w/ paging 처리
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		log.info("getList......" + bno);
		return mapper.getListWithPaging(cri, bno);
	}
	
}
