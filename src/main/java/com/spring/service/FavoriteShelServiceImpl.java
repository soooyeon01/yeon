package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.FavoriteShelDTO;
import com.spring.mapper.FavoriteShelMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteShelServiceImpl implements FavoriteShelService {
	private final FavoriteShelMapper mapper;

	@Override
	public List<FavoriteShelDTO> getLikedShelList(String nickname, PageMaker pageMaker) {

		return mapper.selectLikedShelList(nickname, pageMaker);
	}

	@Override
	public int getLikedShelCount(String nickname) {

		return mapper.selectLikedShelCount(nickname);
	}

	@Override
	public List<FavoriteShelDTO> getLikedShelData(String nickname) {

		return mapper.selectLikedShelData(nickname);
	}

	@Override
	public List<FavoriteShelDTO> getIsLikedShel(String nickname) {

		return mapper.selectIsLikedShel(nickname);
	}

}
