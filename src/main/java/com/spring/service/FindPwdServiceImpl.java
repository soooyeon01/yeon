package com.spring.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.FindPwdMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPwdServiceImpl implements FindPwdService {
	
	private final FindPwdMapper mapper;

	@Override
	public void findPwd(MembersDTO mdto) {
		String result = mapper.findPwd(mdto);
	}
	
	 @Override
	    public int updatePwd(MembersDTO mdto) {
	        return mapper.updatePwd(mdto);
	    }

	 
	 
}
