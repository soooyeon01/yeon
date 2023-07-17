package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface JoinMapper {

	public int registerMembers(MembersDTO mdto);
	public int emailCheck(String email);
	public int nicknameCheck(String nickname);
	public int phoneCheck(int phone);


}
