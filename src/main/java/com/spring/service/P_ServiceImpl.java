package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.P_DTO;
import com.spring.mapper.P_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class P_ServiceImpl implements P_Service {
	private final P_Mapper mapper;

	@Override
	public List<P_DTO> getPetNoticeByPage(String type, String keyword, String region, PageMaker pageMaker) {
		return mapper.PetNoticeByPage(type, keyword, region, pageMaker);
	}

	@Override
	public int getCountPetNotice(String type, String keyword, String region) {
		return mapper.CountPetNotice(type, keyword, region);
	}

	@Override
	public List<P_DTO> getP(int pet_notice_no) {

		return mapper.selectP(pet_notice_no);
	}

	@Override
	public void registerP(String nickname, P_DTO dto) {

		mapper.insertPetnoticeData(nickname, dto);
	}

	@Override
	public int removeP(int pet_notice_no) {

		return mapper.deletePetnoticeData(pet_notice_no);
	}

}
