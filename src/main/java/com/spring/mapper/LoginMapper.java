package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface LoginMapper {

	int selectCountMember(MembersDTO dto);

	void selectMembersByIdAndPwd(MembersDTO dto);
	
}
