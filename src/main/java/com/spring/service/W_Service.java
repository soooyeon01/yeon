package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Service {
	List<W_DTO> getW(int with_pet_no);
	List<W_DTO> getAllBoard();
	
	
	public int getCountAllBoard();
	List<W_DTO> getAllBoardByPage(PageMaker pageMaker);
	List<W_DTO> getRegionWith(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);
	int getCountRegionWith(String region);
	
	
	int registerWithpetData(W_DTO dto);
	int removeWithpetData(int with_pet_no);
	

	
//	//위드펫카테고리
//	List<W_DTO> getCategoryWith(String category3);

}
