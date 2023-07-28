package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface FindPwdMapper {


	int findPwd(MembersDTO mdto);
    int updatePwd(MembersDTO mdto);
	
}
