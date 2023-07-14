package com.spring.api.mapper;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;

public interface ApiMapper {
	public int insertPetData(PetnoticeDTO pdto);
	
	public int insertShelData(ShelterDTO sdto);
	
	public int insertWithData(WithpetDTO wdto);
}
