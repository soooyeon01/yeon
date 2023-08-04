package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.F_W_DTO;
import com.spring.mapper.F_W_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class F_W_ServiceImpl implements F_W_Service {
	private final F_W_Mapper mapper;

	@Override
	public List<F_W_DTO> getWBoardByPage(String nickname, PageMaker pageMaker) {

		return mapper.selectWBoardByPage(nickname, pageMaker);
	}

	@Override
	public int getCountAllBoard(String nickname) {

		return mapper.selectCountWBoard(nickname);
	}

	@Override
	public List<F_W_DTO> getWBoard(String nickname) {

		return mapper.selectWBoard(nickname);
	}

	@Override
	public List<F_W_DTO> getLikedPostIdsByUser(String nickname) {

		return mapper.selectLikedPostIdsByUser(nickname);
	}
}
