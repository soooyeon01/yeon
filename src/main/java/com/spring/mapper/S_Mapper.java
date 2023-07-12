package com.spring.mapper;

import java.util.List;

import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface S_Mapper {
	List<S_DTO> selectS(int shelter_no);
	List<S_DTO> selectAllBoard();
	int selectCountAllBoard();
	List<S_DTO> selectAllBoardByPage(PageMaker pageMaker);
	
	int insertShelterData(S_DTO dto);
	int deleteShelterData(int shelter_no);
}
