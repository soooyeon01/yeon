package com.spring.mapper;

import java.util.List;

import com.spring.domain.ReplyDTO;

public interface ReplyMapper {
	List<ReplyDTO> replyList(int c_no);
	int cntTotal(int c_no);
	int insertReply(ReplyDTO dto);
	int updateReply(ReplyDTO dto);
	int deleteReply(ReplyDTO dto);
}
