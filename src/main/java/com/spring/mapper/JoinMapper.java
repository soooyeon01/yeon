package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface JoinMapper {

	public int registerMembers(MembersDTO mdto);
<<<<<<< HEAD
	public int emailCheck(String email);
	public int nicknameCheck(String nickname);
	public int phoneCheck(int phone);
=======
	int emailCheck(String email);
>>>>>>> f0806e8dec5d65e22667ece8abe1b5c0b0bae3e6

}
