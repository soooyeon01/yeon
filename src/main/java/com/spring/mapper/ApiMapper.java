package com.spring.mapper;


import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;

public interface ApiMapper {
	int insertPetData(P_DTO pdto);
	int insertShelData(S_DTO sdto);
	int insertWithData(W_DTO wdto);
}
