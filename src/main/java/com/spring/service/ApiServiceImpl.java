package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.PetDTO;
import com.spring.domain.ShelDTO;
import com.spring.domain.WithDTO;
import com.spring.mapper.ApiMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService {

	private final ApiMapper mapper;

	@Override
	public int regitsterPetData(PetDTO pdto) {

		return mapper.insertPetData(pdto);
	}

	@Override
	public int removePetData(PetDTO pdto) {

		return mapper.deletePetData(pdto);
	}

	@Override
	public int removePetEnd(PetDTO pdto) {

		return mapper.deletePetEnd(pdto);
	}

	@Override
	public int regitsterShelData(ShelDTO sdto) {

		return mapper.insertShelData(sdto);
	}

	@Override
	public int removeShelData(ShelDTO sdto) {

		return mapper.deleteShelData(sdto);
	}

	@Override
	public int regitsterWithData(WithDTO wdto) {

		return mapper.insertWithData(wdto);
	}

	@Override
	public int removeWithData(WithDTO wdto) {

		return mapper.deleteWithData(wdto);
	}

}
