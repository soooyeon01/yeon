package com.spring.service;

import java.util.List;

import com.spring.domain.MembersDTO;


public interface MypageService {
	

	int modifyMember(MembersDTO mdto);

	List<MembersDTO> getMypage();

	List<MembersDTO> getMypage(String email);

}
