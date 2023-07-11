package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.F_P_DTO;
import com.spring.mapper.F_P_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class F_P_ServiceImpl implements F_P_Service {
	private final F_P_Mapper mapper;
	@Override
	public List<F_P_DTO> getPBoard() {
		
		return mapper.selectPBoard();
	}


	@Override
	public List<F_P_DTO> getPBoardByPage(PageMaker pageMaker) {
		
		return mapper.selectPBoardByPage(pageMaker);
	}

}
