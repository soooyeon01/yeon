package com.spring.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.spring.domain.MembersDTO;

public interface FindPwdService {

	public String findPwd(MembersDTO mdto);
	public int updatePwd(MembersDTO mdto);
	void sendTempPwd(MembersDTO mdto);
}
