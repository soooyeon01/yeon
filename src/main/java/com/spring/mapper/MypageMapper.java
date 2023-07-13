package com.spring.mapper;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MypageMapper {
	
	int updateMypage(MembersDTO mdto);

	List<MembersDTO> selectMypage(String email);

}
