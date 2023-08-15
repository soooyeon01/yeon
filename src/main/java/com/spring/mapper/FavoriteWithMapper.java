package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.FavoriteWithDTO;
import com.spring.util.PageMaker;

public interface FavoriteWithMapper {
	List<FavoriteWithDTO> selectLikedWithList(@Param("nickname") String nickname, @Param("pageMaker") PageMaker pageMaker);

	int selectLikedWithCount(String nickname);

	List<FavoriteWithDTO> selectLikedWithData(String nickname);

	List<FavoriteWithDTO> selectIsLikedWith(String nickname);

}
