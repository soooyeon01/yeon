package com.spring.service;

import java.util.List;

import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Service {
	List<W_DTO> getW(int with_pet_no);
	List<W_DTO> getAllBoard();
	public int getCountAllBoard();
	List<W_DTO> getAllBoardByPage(PageMaker pageMaker);
	
	int registerWithpetData(W_DTO dto);
	int removeWithpetData(int with_pet_no);
	List<W_DTO> getRegionWith(String region);
	
	//위드펫카테고리
	List<W_DTO> getCategoryWith(String category3);
}
