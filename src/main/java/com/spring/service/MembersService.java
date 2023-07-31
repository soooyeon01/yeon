package com.spring.service;

import java.util.List;

import com.spring.domain.MembersDTO;

public interface MembersService {
	List<MembersDTO> getMemberList();
	int memberCnt();
	int kick(String userEmail);
}
