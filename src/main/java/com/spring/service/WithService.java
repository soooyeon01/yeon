package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.WithDTO;
import com.spring.util.PageMaker;

public interface WithService {
	List<WithDTO> getWithList(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3,
			@Param("pageMaker") PageMaker pageMaker);

	int getWithCount(@Param("type") String type, @Param("keyword") String keyword, @Param("region") String region,
			@Param("category3") String category3);

	List<WithDTO> getWithDetail(int with_pet_no);

	void registerWithData(@Param("nickname") String nickname, @Param("dto") WithDTO dto);

	int removeWithData(int with_pet_no);

}
