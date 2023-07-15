package com.spring.api.service;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;

public interface PetService {
	public void registerPetData(PetnoticeDTO pdto);
	
	public String makeApiCall(String apiUrl, String serviceKey);
	public PetnoticeDTO parseData(String response);
	
}
