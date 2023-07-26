package com.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

public interface W_Service {
	List<W_DTO> getW(int with_pet_no);
	List<W_DTO> getAllBoard();
	
	
	public int getCountAllBoard();
	List<W_DTO> getAllBoardByPage(PageMaker pageMaker);
	List<W_DTO> getRegionWith(@Param("region") String region, @Param("category3") String category3, @Param("pageMaker") PageMaker pageMaker);
	int getCountRegionWith(@Param("region") String region,@Param("category3") String category3);
	
	void registerWithpetData(@Param("nickname")String nickname,@Param("dto")W_DTO dto);
	int removeWithpetData(int with_pet_no);
	

	//위드펫카테고리 추가

	List<W_DTO> getCategoryWith(@Param("category3") String category3, @Param("pageMaker") PageMaker pageMaker);
	int getCountCategorywith(String category3);
	


}
