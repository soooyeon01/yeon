package com.spring.mapper;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MypageMapper {
	
	List<MembersDTO> selectMypage(String email);
	
	int UpdateMember(MembersDTO mdto);
}
