package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Service {
	List<W_DTO> getCategorySearchList(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3,
			@Param("pageMaker") PageMaker pageMaker);

	int getCountCategoryWithSearch(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3);

	List<W_DTO> getW(int with_pet_no);

	void registerWithpetData(@Param("nickname") String nickname, @Param("dto") W_DTO dto);

	int removeWithpetData(int with_pet_no);

}
