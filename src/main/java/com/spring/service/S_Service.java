package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.P_DTO;
import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface S_Service {
	List<S_DTO> getS(int shelter_no);
	List<S_DTO> getAllBoard();
	
	List<S_DTO> getAllBoardByPage(PageMaker pageMaker);
	List<S_DTO> getRegionShel(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);
	int getCountRegionShel(String region);
	int getCountAllBoard();
	
	void registerShelterData(@Param("nickname")String nickname,@Param("dto") S_DTO dto);
	int removeShelterData(int shelter_no);
	
	
}
