package com.spring.mapper;

import java.util.List;

import com.spring.domain.NoticeDTO;

public interface NoticeMapper {
	NoticeDTO selectNotice(int notice_no);
	List<NoticeDTO> selectAllNotice();
	int insertNotice(NoticeDTO dto);
	int updateNotice(NoticeDTO dto);
	int deleteNotice(int notice_no);
	int viewCount(int notice_no);
	
}
