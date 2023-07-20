package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.ReplyDTO;
import com.spring.mapper.CommunityMapper;
import com.spring.mapper.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyMapper mapper;
	
	@Override
	public List<ReplyDTO> getReplyList(int c_no) {
		
		return mapper.replyList(c_no);
	}

	@Override
	public int cntTotal(int c_no) {
		
		return mapper.cntTotal(c_no);
	}
	
	@Override
	public int registerReply(ReplyDTO dto) {
		
		return mapper.insertReply(dto);
	}

	@Override
	public int modifyReply(ReplyDTO dto) {
		
		return mapper.updateReply(dto);
	}

	@Override
	public int removeReply(ReplyDTO dto) {
		
		return mapper.deleteReply(dto);
	}

	

}
