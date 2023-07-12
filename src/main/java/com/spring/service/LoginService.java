package com.spring.service;

import com.spring.domain.MembersDTO;

public interface LoginService {

	void selectLogin(MembersDTO mdto);
	int countLogin(MembersDTO mdto);
	
	
}
