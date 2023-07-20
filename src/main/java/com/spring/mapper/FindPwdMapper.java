package com.spring.mapper;

import com.spring.domain.MembersDTO;

public interface FindPwdMapper {


	MembersDTO findPwd(String email);
    int updatePwd(MembersDTO mdto);
	
}
