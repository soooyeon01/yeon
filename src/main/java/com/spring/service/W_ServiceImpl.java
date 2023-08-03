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
	public List<W_DTO> getCategorySearchList(String type, String keyword, String region, String category3,
			PageMaker pageMaker) {
		return mapper.categorySearchList(type, keyword, region, category3, pageMaker);
	}

	@Override
	public int getCountCategoryWithSearch(String type, String keyword, String region, String category3) {
		return mapper.countCategoryWithSearch(type, keyword, region, category3);
	}

	@Override
	public List<W_DTO> getW(int with_pet_no) {
		return mapper.selectW(with_pet_no);
	}

	@Override
	public void registerWithpetData(String nickname, W_DTO dto) {

		mapper.insertWith_petData(nickname, dto);
	}

	@Override
	public int removeWithpetData(int with_pet_no) {

		return mapper.deleteWith_petData(with_pet_no);
	}

}
