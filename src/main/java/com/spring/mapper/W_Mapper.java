package com.spring.mapper;

import java.util.List;

import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Mapper {
	List<W_DTO> selectW(int with_pet_no);
	List<W_DTO> selectAllBoard();
	int seletCountAllBoard();
	List<W_DTO> selectAllBoardByPage(PageMaker pageMaker);
}
