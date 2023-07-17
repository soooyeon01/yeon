package com.spring.service;

import java.util.List;

import com.spring.domain.NoticeDTO;

public interface NoticeService {
	NoticeDTO getNotice(int notice_no);
	List<NoticeDTO> getAllNotice();
	
	int registerNotice(NoticeDTO dto);
	int modifyNotice(NoticeDTO dto);
	int removeNotice(int notice_no);
	int viewCount(int notice_no);
}
