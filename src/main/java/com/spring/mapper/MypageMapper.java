package com.spring.mapper;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MypageMapper {
	
	int updatePwd(MembersDTO dto);
	
	int updatePhone(MembersDTO dto);

	List<MembersDTO> selectMypage(String email);
	
	int removeMember(String pwd);

	String selectPwd(String email);

	List<MembersDTO> selectMembers();

}
