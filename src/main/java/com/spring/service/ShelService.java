package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.ShelDTO;
import com.spring.util.PageMaker;

public interface ShelService {
	List<ShelDTO> getShelList(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);

	int getShelCount(String region);

	List<ShelDTO> getShelDetail(int shelter_no);

	void registerShelData(@Param("nickname") String nickname, @Param("dto") ShelDTO dto);

	int removeShelData(int shelter_no);

}
