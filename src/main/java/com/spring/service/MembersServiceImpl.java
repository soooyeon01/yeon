package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.MembersMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {
	private final MembersMapper mapper;
	
	@Override
	public List<MembersDTO> getMemberList() {
		// TODO Auto-generated method stub
		return mapper.selectMembersList();
	}

	@Override
	public int kick(String userEmail) {
		int result=mapper.kick(userEmail);
		return result;
	}

	@Override
	public int memberCnt() {
		// TODO Auto-generated method stub
		return mapper.memberCnt();
	}
}
