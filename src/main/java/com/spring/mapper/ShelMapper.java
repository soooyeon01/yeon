package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.ShelDTO;
import com.spring.util.PageMaker;

public interface ShelMapper {

	List<ShelDTO> selectShelList(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);

	int selectShelCount(String region);

	List<ShelDTO> selectShelDetail(int shelter_no);

	void insertShelData(@Param("nickname") String nickname, @Param("dto") ShelDTO dto);

	int deleteShelData(int shelter_no);

}
