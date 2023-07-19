package com.spring.service;

import com.spring.domain.MembersDTO;

public interface FindPwdService {

	String findPwd(MembersDTO mdto);
    int updatePwd(MembersDTO mdto);
}
