package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface LoginMapper {

	public void selectLogin(MembersDTO mdto);
	
	public int countLogin(MembersDTO mdto);
	
}
