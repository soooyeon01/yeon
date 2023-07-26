package com.spring.service;


import org.springframework.stereotype.Service;

import com.spring.util.SendEmail;
import com.spring.domain.MembersDTO;
import com.spring.mapper.FindPwdMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindPwdServiceImpl implements FindPwdService {
	
	private final FindPwdMapper mapper;

	@Override
	public String findPwd(MembersDTO mdto) {
		String result = mapper.findPwd(mdto);
		return result;
	}

	 @Override
	    public int updatePwd(MembersDTO mdto) {
	        return mapper.updatePwd(mdto);
	    }
	 
	 @Override
	 public void sendTempPwd(MembersDTO mdto) {
	     String email = mdto.getEmail();
	     String tempPwd = generateTempPwd();
	     mdto.setTempPwd(tempPwd);
	     mapper.updateTempPwd(mdto);

	     SendEmail.naverMailSend(email, tempPwd);
	 }
	 
}
