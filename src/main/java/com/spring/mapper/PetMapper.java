package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.PetDTO;
import com.spring.util.PageMaker;

public interface PetMapper {

	List<PetDTO> selectPetList(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);

	int selectPetCount(@Param("type") String type, @Param("keyword") String keyword, @Param("region") String region);

	List<PetDTO> selectPetDetail(int pet_notice_no); // petdeatil

	void insertPetData(@Param("nickname") String nickname, @Param("dto") PetDTO dto);

	int deletePetData(int pet_notice_no);

}
