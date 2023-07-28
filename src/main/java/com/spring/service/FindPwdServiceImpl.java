package com.spring.service;


import java.util.Random;

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
	public int findPwd(MembersDTO mdto) {
	    return mapper.findPwd(mdto);
	}
	
	 @Override
	    public int updatePwd(MembersDTO mdto) {
	        return mapper.updatePwd(mdto);
	    }
	 
	 @Override
	    public void sendTempPwd(String email, String tempPwd) {
	        SendEmail.naverMailSend(email, tempPwd);
	    }
	
	 public String generateTempPwd() {
		    int passwordLength = 8; // 비밀번호 길이
		    char[] password = new char[passwordLength];

		    String charString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; // 사용 가능한 문자

		    Random random = new Random();
		    for (int i = 0; i < passwordLength; i++) {
		        password[i] = charString.charAt(random.nextInt(charString.length()));
		    }

		    return new String(password);
		}
}
