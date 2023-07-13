package com.spring.service;

import java.util.List;

import com.spring.domain.MembersDTO;


public interface MypageService {
	
		
	List<MembersDTO> getMypage(String email);
	int modifyMypage(List<MembersDTO> mdto);
	List<MembersDTO> modifyMypage(MembersDTO mdto);

}
