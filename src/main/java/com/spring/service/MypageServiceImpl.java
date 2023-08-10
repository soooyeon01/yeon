package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.MembersMapper;
import com.spring.mapper.MypageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {
	
	private final MypageMapper mapper;


	@Override
	public List<MembersDTO> getMypage(String email) {
		List<MembersDTO> result = mapper.selectMypage(email);
		return result;
	}

	@Override
	public int modifyPwd(MembersDTO dto) {
		int result = mapper.updatePwd(dto);
		return result;
	}

	@Override
	public int modifyPhone(MembersDTO dto) {
		int result = mapper.updatePhone(dto);
		return result;
	}
	
	//탈퇴
	@Override
	public int removeMember(String email) {
		int result = mapper.removeMember(email);
		return result;
	}

	@Override
	public String getPwd(String email) {	
		return mapper.selectPwd(email);
	}

	@Override
	public List<MembersDTO> getPhoneC() {
		return mapper.selectMembers();
	}


	

	
}
