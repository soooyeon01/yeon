package com.spring.mapper;

import java.util.List;

import com.spring.domain.CommunityAttachDTO;

public interface CommunityAttachMapper {
	public void insert(CommunityAttachDTO dto);
	public void delete(String uuid);
	public List<CommunityAttachDTO> findByCno(int c_no);
}
