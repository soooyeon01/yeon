package com.spring.service;

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;

public interface ApiService {
	int regitsterPetData(P_DTO pdto);
	int regitsterShelData(S_DTO sdto);
	int regitsterWithData(W_DTO wdto);
	
	int removePetData(P_DTO pdto);
	int removeShelData(S_DTO sdto);
	int removeWithData(W_DTO wdto);
	
	int removePetEnd(P_DTO pdto);
}
