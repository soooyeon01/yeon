package com.spring.service;

import java.util.List;

import com.spring.domain.F_P_DTO;
import com.spring.domain.F_S_DTO;
import com.spring.util.PageMaker;

public interface F_P_Service {
	List<F_P_DTO> getPBoard();
	List<F_P_DTO> getPBoardByPage(PageMaker pageMaker);
	public int getCountAllBoard();
}
