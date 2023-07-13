package com.spring.mapper;

import java.util.List;

import com.spring.domain.F_W_DTO;
import com.spring.util.PageMaker;

public interface F_W_Mapper {
	List<F_W_DTO> selectWBoard();
	int selectCountWBoard();	
	List<F_W_DTO> selectWBoardByPage(PageMaker pageMaker);
}
