package com.spring.service;

import java.util.List;

import com.spring.domain.ReplyDTO;

public interface ReplyService {
	List<ReplyDTO> getReplyList(int c_no);
	int registerReply(ReplyDTO dto);
	int modifyReply(ReplyDTO dto);
	int removeReply(ReplyDTO dto);
}
