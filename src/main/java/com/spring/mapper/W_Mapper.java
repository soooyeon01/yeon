package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Mapper {
	List<W_DTO> categorySearchList(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3,
			@Param("pageMaker") PageMaker pageMaker);

	int countCategoryWithSearch(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3);

	List<W_DTO> selectW(int with_pet_no);

	void insertWith_petData(@Param("nickname") String nickname, @Param("dto") W_DTO dto);

	int deleteWith_petData(int with_pet_no);

}
