package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.ShelDTO;
import com.spring.mapper.ShelMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShelServiceImpl implements ShelService {
	private final ShelMapper mapper;

	@Override
	public List<ShelDTO> getShelList(String region, PageMaker pageMaker) {

		return mapper.selectShelList(region, pageMaker);
	}

	@Override
	public int getShelCount(String region) {
		return mapper.selectShelCount(region);
	}

	@Override
	public List<ShelDTO> getShelDetail(int shelter_no) {

		return mapper.selectShelDetail(shelter_no);
	}

	@Override
	public void registerShelData(String nickname, ShelDTO dto) {

		mapper.insertShelData(nickname, dto);
	}

	@Override
	public int removeShelData(int shelter_no) {

		return mapper.deleteShelData(shelter_no);
	}

}
