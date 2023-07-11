package com.spring.service;

import com.spring.domain.MembersDTO;

public interface LoginService {

	int getCountMember(MembersDTO mdto);

	void getMembersByIdAndPwd(MembersDTO mdto);
	
}
