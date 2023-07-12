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
	public void selectLogin(MembersDTO mdto) {
		mapper.selectLogin(mdto);
	}

}
