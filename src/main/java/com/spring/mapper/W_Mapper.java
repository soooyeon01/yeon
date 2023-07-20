package com.spring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Mapper {
	List<W_DTO> selectW(int with_pet_no);
	List<W_DTO> selectAllBoard();
	public int selectCountAllBoard();
	List<W_DTO> selectAllBoardByPage(PageMaker pageMaker);
	
	int insertWith_petData(W_DTO dto);
	int deleteWith_petData(int with_pet_no);
	 List<W_DTO> selectRegionWith( String region);
	//public int selectRegionWithCount(String region);
	
}
