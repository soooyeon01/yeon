package com.spring.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.spring.domain.MembersDTO;

public interface FindPwdService {

	public void findPwd(MembersDTO mdto);
	public int updatePwd(MembersDTO mdto);
	public void sendEmail(MembersDTO mdto, String div);
	public void findPwd(HttpServletResponse response, MembersDTO mdto) throws IOException;
}
