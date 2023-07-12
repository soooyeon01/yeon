package com.spring.service;

import com.spring.domain.CommunityDTO;

public interface CommunityApiService {
	int registerCommunity(CommunityDTO dto);
	int modifyCommunity(CommunityDTO dto);
	int removeCommunity(int parseInt);
}
