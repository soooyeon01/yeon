package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.NoticeDTO;
import com.spring.mapper.NoticeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	private final NoticeMapper mapper;
	@Override
	public NoticeDTO getNotice(int notice_no) {
		
		return mapper.selectNotice(notice_no);
	}

	@Override
	public List<NoticeDTO> getAllNotice() {
		
		return mapper.selectAllNotice();
	}

	@Override
	public int registerNotice(NoticeDTO dto) {
		
		return mapper.insertNotice(dto);
	}

	@Override
	public int modifyNotice(NoticeDTO dto) {
		
		return mapper.updateNotice(dto);
	}

	@Override
	public int removeNotice(int notice_no) {
		
		return mapper.deleteNotice(notice_no);
	}

	@Override
	public int viewCount(int notice_no) {
	
		return mapper.viewCount(notice_no);
	}

}
