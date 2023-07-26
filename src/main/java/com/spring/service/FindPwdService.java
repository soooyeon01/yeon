package com.spring.service;


import com.spring.domain.MembersDTO;

public interface FindPwdService {

	public String findPwd(MembersDTO mdto);
	public int updatePwd(MembersDTO mdto);
	void sendTempPwd(String email, String tempPwd);
	String generateTempPwd();
}
