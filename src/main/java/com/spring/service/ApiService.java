package com.spring.service;

import com.spring.domain.PetDTO;

import com.spring.domain.ShelDTO;
import com.spring.domain.WithDTO;

public interface ApiService {

	int regitsterPetData(PetDTO pdto);

	int removePetData(PetDTO pdto);

	int removePetEnd(PetDTO pdto);

	int regitsterShelData(ShelDTO sdto);

	int removeShelData(ShelDTO sdto);

	int regitsterWithData(WithDTO wdto);

	int removeWithData(WithDTO wdto);

}
