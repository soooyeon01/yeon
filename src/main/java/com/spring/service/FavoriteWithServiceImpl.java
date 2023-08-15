package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.FavoriteWithDTO;
import com.spring.mapper.FavoriteWithMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FavoriteWithServiceImpl implements FavoriteWithService {
	private final FavoriteWithMapper mapper;

	@Override
	public List<FavoriteWithDTO> getLikedWithList(String nickname, PageMaker pageMaker) {

		return mapper.selectLikedWithList(nickname, pageMaker);
	}

	@Override
	public int getLikedWithCount(String nickname) {

		return mapper.selectLikedWithCount(nickname);
	}

	@Override
	public List<FavoriteWithDTO> getLikedWithData(String nickname) {

		return mapper.selectLikedWithData(nickname);
	}

	@Override
	public List<FavoriteWithDTO> getIsLikedWith(String nickname) {

		return mapper.selectIsLikedWith(nickname);
	}
}
