package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.FavoriteShelDTO;
import com.spring.util.PageMaker;

public interface FavoriteShelMapper {
	List<FavoriteShelDTO> selectLikedShelList(@Param("nickname") String nickname, @Param("pageMaker") PageMaker pageMaker);

	int selectLikedShelCount(String nickname);

	List<FavoriteShelDTO> selectLikedShelData(String nickname);

	List<FavoriteShelDTO> selectIsLikedShel(String nickname);

}
