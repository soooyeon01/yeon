package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.CommunityDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityApiServiceImpl implements CommunityApiService {
	private final CommunityApiService mapper;
	@Override
	public int registerCommunity(CommunityDTO dto) {
		
		return mapper.registerCommunity(dto);
	}

	@Override
	public int modifyCommunity(CommunityDTO dto) {
	
		return mapper.modifyCommunity(dto);
	}

	@Override
	public int removeCommunity(int parseInt) {

		return mapper.removeCommunity(parseInt);
	}

}
