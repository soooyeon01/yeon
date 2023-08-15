package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.FavoritePetDTO;
import com.spring.util.PageMaker;

public interface FavoritePetMapper {
	List<FavoritePetDTO> selectLikedPetList(@Param("nickname") String nickname, @Param("pageMaker") PageMaker pageMaker);

	int selectLikedPetCount(String nickname);

	List<FavoritePetDTO> selectLikedPetData(String nickname);

	List<FavoritePetDTO> selectIsLikedPet(String nickname);

}
