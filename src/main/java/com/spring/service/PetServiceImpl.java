package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.PetDTO;
import com.spring.mapper.PetMapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
	private final PetMapper mapper;

	@Override
	public List<PetDTO> getPetList(String type, String keyword, String region, PageMaker pageMaker) {
		return mapper.selectPetList(type, keyword, region, pageMaker);
	}

	@Override
	public int getPetCount(String type, String keyword, String region) {
		return mapper.selectPetCount(type, keyword, region);
	}

	@Override
	public List<PetDTO> getPetDetail(int pet_notice_no) {

		return mapper.selectPetDetail(pet_notice_no);
	}

	@Override
	public void registerPetData(String nickname, PetDTO dto) {

		mapper.insertPetData(nickname, dto);
	}

	@Override
	public int removePetData(int pet_notice_no) {

		return mapper.deletePetData(pet_notice_no);
	}

}
