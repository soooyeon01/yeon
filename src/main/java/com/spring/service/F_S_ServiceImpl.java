package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.F_S_DTO;
import com.spring.mapper.F_S_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class F_S_ServiceImpl implements F_S_Service {
	private final F_S_Mapper mapper;
	@Override
	public List<F_S_DTO> getSBoard() {
		
		return mapper.selectSBoard();
	}

	@Override
	public List<F_S_DTO> getSBoardByPage(PageMaker pageMaker) {
		
		return mapper.selectSBoardByPage(pageMaker);
	}

	@Override
	public int getCountAllBoard() {
		
		return mapper.selectCountSBoard();
	}

}
