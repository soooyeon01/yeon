package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.mapper.ApiMapper;
import com.spring.service.ApiService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
	
	private final ApiMapper mapper;
	@Override
	public int regitsterPetData(P_DTO pdto) {
		
		return mapper.insertPetData(pdto);
	}

	@Override
	public int regitsterShelData(S_DTO sdto) {
		
		return mapper.insertShelData(sdto);
	}

	@Override
	public int regitsterWithData(W_DTO wdto) {
		
		return mapper.insertWithData(wdto);
	}

}