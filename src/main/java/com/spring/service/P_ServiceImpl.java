package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.F_P_DTO;
import com.spring.domain.P_DTO;
import com.spring.mapper.P_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class P_ServiceImpl implements P_Service {
	private final P_Mapper mapper;
	@Override
	public List<P_DTO> getP(int pet_notice_no) {
	
		return mapper.selectP(pet_notice_no);
	}

	@Override
	public List<P_DTO> getAllBoard() {
		
		return mapper.selectAllBoard();
	}

//지역별 조회
	@Override
	public List<P_DTO> getAllBoardByPage(PageMaker pageMaker) {
		return mapper.selectAllBoardByPage(pageMaker);
	}
	@Override
	public List<P_DTO> getRegionPet(String region, PageMaker pageMaker) {
		
		return mapper.selectRegionPet(region, pageMaker);
	}

	@Override
	public int getCountAllBoard() {
		int result = mapper.selectCountAllBoard();
		return result;
	}
	@Override
	public int getCountRegionPet(String region) {
		return mapper.selectCountRegionPet(region);
	}
// 즐겨찾기	
	@Override
	public void registerP(String nickname, P_DTO dto) {
		
		mapper.insertPetnoticeData(nickname, dto);
	}

	@Override
	public int removeP(int pet_notice_no) {
		
		return mapper.deletePetnoticeData(pet_notice_no);
	}

//	public List<P_DTO> getRegionPet(String region) {
//	    List<P_DTO> petList = mapper.selectRegionPet(region);
//
//	    
//	    return petList;
//	}

	
		

}
