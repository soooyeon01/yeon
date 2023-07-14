package com.spring.api.service;

import com.spring.api.domain.PetnoticeDTO;
import com.spring.api.domain.ShelterDTO;
import com.spring.api.domain.WithpetDTO;

public interface ShelService {
	
	public int registerShelData(ShelterDTO sdto);
	public String makeApiCall(String apiUrl, String serviceKey);
	public ShelterDTO parseData(String response);
	
}
