package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface LoginMapper {

	String selectLogin(MembersDTO mdto);
	
}
