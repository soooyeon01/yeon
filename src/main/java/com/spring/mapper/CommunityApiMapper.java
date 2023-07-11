package com.spring.mapper;

import com.spring.domain.CommunityDTO;

public interface CommunityApiMapper {
	int insertCommunity(CommunityDTO dto);
	int updateCommunity(CommunityDTO dto);
	int deleteCommunity(int c_no);
}
