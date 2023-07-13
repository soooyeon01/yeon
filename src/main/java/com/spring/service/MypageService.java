package com.spring.service;

import java.util.List;

import com.spring.domain.MembersDTO;


public interface MypageService {
	
		
	List<MembersDTO> getMypage(String email);
	
	int modifyPwd(MembersDTO dto);

	int modifyPhone(MembersDTO dto);
	
	int removeMember(MembersDTO dto);

}
