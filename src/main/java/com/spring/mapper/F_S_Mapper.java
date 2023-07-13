package com.spring.mapper;

import java.util.List;

import com.spring.domain.F_S_DTO;
import com.spring.util.PageMaker;

public interface F_S_Mapper {
	List<F_S_DTO> selectSBoard();
	int selectCountSBoard();
	List<F_S_DTO> selectSBoardByPage(PageMaker pageMaker);
}
