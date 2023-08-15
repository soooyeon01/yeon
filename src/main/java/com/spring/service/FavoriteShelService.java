package com.spring.service;

import java.util.List;

import com.spring.domain.FavoriteShelDTO;
import com.spring.util.PageMaker;

public interface FavoriteShelService {

	List<FavoriteShelDTO> getLikedShelList(String nickname, PageMaker pageMaker);

	public int getLikedShelCount(String nickname);

	List<FavoriteShelDTO> getLikedShelData(String nickname);

	List<FavoriteShelDTO> getIsLikedShel(String nickname);
}
