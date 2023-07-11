package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface LoginMapper {

	int selectCountMember(MembersDTO mdto);

	void selectMembersByIdAndPwd(MembersDTO mdto);
	
}
