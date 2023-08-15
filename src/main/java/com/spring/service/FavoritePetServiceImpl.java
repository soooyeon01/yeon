package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.FavoritePetDTO;
import com.spring.mapper.FavoritePetMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoritePetServiceImpl implements FavoritePetService {
	private final FavoritePetMapper mapper;

	@Override
	public List<FavoritePetDTO> getLikedPetList(String nickname, PageMaker pageMaker) {

		return mapper.selectLikedPetList(nickname, pageMaker);
	}

	@Override
	public int getLikedPetCount(String nickname) {

		return mapper.selectLikedPetCount(nickname);
	}

	@Override
	public List<FavoritePetDTO> getLikedPetData(String nickname) {

		return mapper.selectLikedPetData(nickname);
	}

	@Override
	public List<FavoritePetDTO> getIsLikedPet(String nickname) {

		return mapper.selectIsLikedPet(nickname);
	}

}
