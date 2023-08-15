package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.PetDTO;
import com.spring.util.PageMaker;

public interface PetService {
	List<PetDTO> getPetList(@Param("type") String type, @Param("keyword") String keyword, @Param("region") String region,
			@Param("pageMaker") PageMaker pageMaker);

	int getPetCount(@Param("type") String type, @Param("keyword") String keyword, @Param("region") String region);

	List<PetDTO> getPetDetail(int pet_notice_no);

	void registerPetData(@Param("nickname") String nickname, @Param("dto") PetDTO dto);

	int removePetData(int pet_notice_no);

}
