package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface JoinMapper {

	public int registerMembers(MembersDTO mdto);
	int emailCheck(String email);

}
