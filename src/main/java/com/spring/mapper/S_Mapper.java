package com.spring.mapper;

import java.util.List;

import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface S_Mapper {
	List<S_DTO> selectS(int shelter_no);
	List<S_DTO> selectAllBoard();
	int seletCountAllBoard();
	List<S_DTO> selectAllBoardByPage(PageMaker pageMaker);
}
