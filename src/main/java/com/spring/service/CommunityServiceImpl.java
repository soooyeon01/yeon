package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.CommunityDTO;
import com.spring.mapper.CommunityMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

	private final CommunityMapper mapper;
	
	@Override
	public CommunityDTO getCommunity(int c_no) {
		
		return mapper.selectCommunity(c_no);
	}

	@Override
	public List<CommunityDTO> getAllCommunity() {
		
		return mapper.selectAllCommunity();
	}


	@Override
	public List<CommunityDTO> getAllCommunityByPage(PageMaker pageMaker) {
		// TODO Auto-generated method stub
		return mapper.selectAllCommunityByPage(pageMaker);
	}
	
	@Override
	public int registerCommunity(CommunityDTO dto) {
		
		return mapper.insertCommunity(dto);
	}

	@Override
	public int modifyCommunity(CommunityDTO dto) {
	
		return mapper.updateCommunity(dto);
	}

	@Override
	public int removeCommunity(int parseInt) {

		return mapper.deleteCommunity(parseInt);
	}

}
