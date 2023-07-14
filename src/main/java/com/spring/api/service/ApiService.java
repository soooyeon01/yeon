package com.spring.api.service;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;

public interface ApiService {
	public int registerPetData(PetnoticeDTO pdto);
	public int registerShelData(ShelterDTO sdto);
	public int registerWithData(WithpetDTO wdto);
	
}
