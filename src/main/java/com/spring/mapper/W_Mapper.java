package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.S_DTO;
import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Mapper {
	List<W_DTO> selectW(int with_pet_no);
	List<W_DTO> selectAllBoard();
	
	List<W_DTO> selectAllBoardByPage(PageMaker pageMaker);
	List<W_DTO> selectRegionWith(@Param("region") String region, @Param("pageMaker") PageMaker pageMaker);
	public int selectCountAllBoard();
	int selectCountRegionWith(String region);
	
	int insertWith_petData(W_DTO dto);
	int deleteWith_petData(int with_pet_no);

	


	
	//위드펫카테고리
	List<W_DTO> selectCategoryWith(@Param("pageMaker") PageMaker pageMaker, @Param("category3") String category3);
}
