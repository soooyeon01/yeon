package com.spring.service;

import java.util.List;

import com.spring.domain.FavoritePetDTO;
import com.spring.util.PageMaker;

public interface FavoritePetService {

	List<FavoritePetDTO> getLikedPetList(String nickname, PageMaker pageMaker);

	public int getLikedPetCount(String nickname);

	List<FavoritePetDTO> getLikedPetData(String nickname);

	List<FavoritePetDTO> getIsLikedPet(String nickname);
}
