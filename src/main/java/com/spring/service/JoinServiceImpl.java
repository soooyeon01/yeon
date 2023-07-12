package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.CommunityMapper;
import com.spring.mapper.JoinMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl  implements JoinService{
	private final JoinMapper mapper;

	@Override
	public void registerMembers(MembersDTO mdto) {
		mapper.registerMembers(mdto);
		
	}

}
