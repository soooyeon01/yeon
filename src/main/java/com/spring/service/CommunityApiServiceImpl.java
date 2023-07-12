package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.CommunityDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityApiServiceImpl implements CommunityApiService {
	private final CommunityApiService service;
	@Override
	public int registerCommunity(CommunityDTO dto) {
		
		return service.registerCommunity(dto);
	}

	@Override
	public int modifyCommunity(CommunityDTO dto) {
	
		return service.modifyCommunity(dto);
	}

	@Override
	public int removeCommunity(int parseInt) {

		return service.removeCommunity(parseInt);
	}

}
