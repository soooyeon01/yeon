package com.spring.api.service;

import org.springframework.stereotype.Service;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;
import com.spring.api.mapper.ApiMapper;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {
	private final ApiMapper mapper;
	@Override
	public int registerPetData(PetnoticeDTO pdto) {
		
		return mapper.insertPetData(pdto);
	}

	@Override
	public int registerShelData(ShelterDTO sdto) {
		
		return mapper.insertShelData(sdto);
	}

	@Override
	public int registerWithData(WithpetDTO wdto) {
		
		return mapper.insertWithData(wdto);
	}

}
