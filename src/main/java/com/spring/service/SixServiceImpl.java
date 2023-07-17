package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.W_DTO;
import com.spring.mapper.SixMapper;
import com.spring.mapper.W_Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SixServiceImpl implements SixService {
	private final SixMapper mapper;
	
	@Override
	public List<W_DTO> getRC(String category3) {
		// TODO Auto-generated method stub
		return mapper.selectRC(category3);
	}

	@Override
	public List<W_DTO> getAC(String category3) {
		// TODO Auto-generated method stub
		return mapper.selectAC(category3);
	}

	@Override
	public List<W_DTO> getCU(String category3) {
		// TODO Auto-generated method stub
		return mapper.selectCU(category3);
	}

	@Override
	public List<W_DTO> getHP(String category3) {
		// TODO Auto-generated method stub
		return mapper.selectHP(category3);
	}

	@Override
	public List<W_DTO> getGO(String category3) {
		// TODO Auto-generated method stub
		return mapper.selectGO(category3);
	}

	@Override
	public List<W_DTO> getCA(String category3) {
		// TODO Auto-generated method stub
		return null;
	}

}
