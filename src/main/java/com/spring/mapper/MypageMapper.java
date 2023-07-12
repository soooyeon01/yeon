package com.spring.mapper;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MypageMapper {
	
	List<MembersDTO> selectMypage();
	
	int UpdateMember(MembersDTO mdto);

	List<MembersDTO> selectMypage(String id);

}
