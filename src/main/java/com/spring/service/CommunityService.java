package com.spring.service;

import java.util.List;

import com.spring.domain.CommunityDTO;
import com.spring.util.PageMaker;

public interface CommunityService {
	CommunityDTO getCommunity(int c_no);
	List<CommunityDTO> getAllCommunity();

	int getCountAllCommunity();

	List<CommunityDTO> getAllCommunityByPage(PageMaker pageMaker);
}
