package com.spring.mapper;

import com.spring.domain.PetDTO;
import com.spring.domain.ShelDTO;
import com.spring.domain.WithDTO;

public interface ApiMapper {
	int insertPetData(PetDTO pdto);

	int insertShelData(ShelDTO sdto);

	int insertWithData(WithDTO wdto);

	int deletePetData(PetDTO pdto);

	int deletePetEnd(PetDTO pdto);

	int deleteShelData(ShelDTO sdto);

	int deleteWithData(WithDTO wdto);

}
