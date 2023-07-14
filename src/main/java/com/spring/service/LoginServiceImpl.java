package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
	private final LoginMapper mapper;


	@Override
	public MembersDTO selectLogin(MembersDTO mdto) {
		return mapper.selectLogin(mdto);
	}
	
	@Override
	public int countLogin(MembersDTO mdto) {
		return mapper.countLogin(mdto);
		
	}
	

}
