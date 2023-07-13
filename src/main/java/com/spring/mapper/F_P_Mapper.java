package com.spring.mapper;

import java.util.List;

import com.spring.domain.F_P_DTO;
import com.spring.util.PageMaker;

public interface F_P_Mapper {
	List<F_P_DTO> selectPBoard();
	int selectCountPBoard();
	List<F_P_DTO> selectPBoardByPage(PageMaker pageMaker);
}
