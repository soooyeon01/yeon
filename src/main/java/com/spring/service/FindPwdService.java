package com.spring.service;

import com.spring.domain.MembersDTO;

public interface FindPwdService {

	public void findPwd(MembersDTO mdto);
	public int updatePwd(MembersDTO mdto);
}
