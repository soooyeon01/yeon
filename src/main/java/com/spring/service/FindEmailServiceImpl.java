package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.FindEmailMapper;
import com.spring.mapper.LoginMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindEmailServiceImpl implements FindEmailService {
	
	private final FindEmailMapper mapper;

	@Override
	public String findEmail(MembersDTO mdto) {
		String result = mapper.findEmail(mdto);
		return result;
	}

}
