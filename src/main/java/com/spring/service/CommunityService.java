package com.spring.service;

import java.util.List;

import com.spring.domain.CommunityDTO;
import com.spring.util.PageMaker;

public interface CommunityService {
	CommunityDTO getCommunity(int c_no);
	List<CommunityDTO> getAllCommunity();
	List<CommunityDTO> getMyCommunity(String nickname);
	List<CommunityDTO> getAllCommunityByPage(PageMaker pageMaker);
	
	int registerCommunity(CommunityDTO dto);
	int modifyCommunity(CommunityDTO dto);
	int removeCommunity(int c_no);
	int viewCount(int c_no);
}
