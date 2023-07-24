package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.mapper.W_Mapper;
import com.spring.util.Criteria;
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
	public List<W_DTO> getRegionWith(String region, PageMaker pageMaker) {
		
		return mapper.selectRegionWith(region, pageMaker);
	}
	
	@Override
	public int getCountRegionWith(String region) {
		return mapper.selectCountRegionWith(region);
	}
	
	
	@Override
	public List<W_DTO> getAllBoardByPage(PageMaker pageMaker) {
		return mapper.selectAllBoardByPage(pageMaker);
	}

	@Override
	public int getCountAllBoard() {
		
		return mapper.selectCountAllBoard();
	}
	//즐겨찾기
	@Override
	public int registerWithpetData(W_DTO dto) {
		
		return mapper.insertWith_petData(dto);
	}

	@Override
	public int removeWithpetData(int with_pet_no) {
		
		return mapper.deleteWith_petData(with_pet_no);
	}

	



	//위드펫카테고리
	/* 추가 */
	@Override
	public List<W_DTO> getCategoryWith(String category3, PageMaker pageMaker) {
		return mapper.selectCategoryWith(category3, pageMaker);
	}

	@Override
	public int getCountCategorywith(String category3) {
		return mapper.selectCountCategoryWith(category3);
	}



}
