package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.W_DTO;
import com.spring.mapper.W_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class W_ServiceImpl implements W_Service {
	private final W_Mapper mapper;
	@Override
	public List<W_DTO> getW(int with_pet_no) {
		return mapper.selectW(with_pet_no);
	}

	@Override
	public List<W_DTO> getAllBoard() {
		return mapper.selectAllBoard();
	}

	@Override
	public List<W_DTO> getAllBoardByPage(PageMaker pageMaker) {
		return mapper.selectAllBoardByPage(pageMaker);
	}
}
