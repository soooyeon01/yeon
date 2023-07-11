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
	public int getCountMember(MembersDTO mdto) {
		int result = mapper.selectCountMember(mdto);
		return result;
	}

	@Override
	public void getMembersByIdAndPwd(MembersDTO mdto) {
		mapper.selectCountMember(mdto);
	}

}
