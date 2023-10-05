package com.spring.mapper;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MembersMapper {
	
	List<MembersDTO> selectMembersList();
	int memberCnt();
	int kick(String userEmail);
}
//