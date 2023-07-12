package com.spring.service;

import java.util.List;

import com.spring.domain.MembersDTO;


public interface MypageService {
	
//	List<MembersDTO> getMypage(String email);
	
	int modifyMember(MembersDTO mdto);

	List<MembersDTO> getMypage();

}
