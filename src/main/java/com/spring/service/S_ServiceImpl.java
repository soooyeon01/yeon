package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.S_DTO;
import com.spring.mapper.S_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class S_ServiceImpl implements S_Service {
	private final S_Mapper mapper;
	@Override
	public List<S_DTO> getS(int shelter_no) {
	
		return mapper.selectS(shelter_no);
	}

	@Override
	public List<S_DTO> getAllBoard() {
	
		return mapper.selectAllBoard();
	}


	@Override
	public List<S_DTO> getAllBoardByPage(PageMaker pageMaker) {
	
		return mapper.selectAllBoardByPage(pageMaker);
	}

	@Override
	public int getCountAllBoard() {
		
		return mapper.selectCountAllBoard();
	}

	@Override
	public int registerShelterData(S_DTO dto) {
		
		return mapper.insertShelterData(dto);
	}

	@Override
	public int removeShelterData(int shelter_no) {
		
		return mapper.deleteShelterData(shelter_no);
	}

	@Override
	public int getRegionShel(S_DTO dto) {
		
		return mapper.selectRegionShel(dto);
	}

}
