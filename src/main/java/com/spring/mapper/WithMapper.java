package com.spring.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.spring.domain.WithDTO;
import com.spring.util.PageMaker;

public interface WithMapper {
	List<WithDTO> selectWithList(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3,
			@Param("pageMaker") PageMaker pageMaker);

	int selectWithCount(@Param("type") String type, @Param("keyword") String keyword,
			@Param("region") String region, @Param("category3") String category3);

	List<WithDTO> selectWithDetail(int with_pet_no);

	void insertWithData(@Param("nickname") String nickname, @Param("dto") WithDTO dto);

	int deleteWithData(int with_pet_no);

}
