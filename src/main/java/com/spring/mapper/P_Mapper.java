package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface P_Mapper {

	List<P_DTO> PetNoticeByPage(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);

	int CountPetNotice(@Param("type") String type, @Param("keyword") String keyword, @Param("region") String region);

	List<P_DTO> selectP(int pet_notice_no); // petdeatil

	void insertPetnoticeData(@Param("nickname") String nickname, @Param("dto") P_DTO dto);

	int deletePetnoticeData(int pet_notice_no);

}
