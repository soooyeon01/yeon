package com.spring.service;

import com.spring.domain.MembersDTO;

public interface LoginService {

	MembersDTO selectLogin(MembersDTO mdto);
	int countLogin(MembersDTO mdto);
	
	
}
