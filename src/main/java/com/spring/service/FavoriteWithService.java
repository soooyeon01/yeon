package com.spring.service;

import java.util.List;

import com.spring.domain.FavoriteWithDTO;
import com.spring.util.PageMaker;

public interface FavoriteWithService {

	List<FavoriteWithDTO> getLikedWithList(String nickname, PageMaker pageMaker);

	int getLikedWithCount(String nickname);

	List<FavoriteWithDTO> getLikedWithData(String nickname);

	List<FavoriteWithDTO> getIsLikedWith(String nickname);
}
