package com.spring.service;

import java.util.List;

import com.spring.domain.F_S_DTO;
import com.spring.util.PageMaker;

public interface F_S_Service {
	List<F_S_DTO> getSBoard();
	List<F_S_DTO> getSBoardByPage(PageMaker pageMaker);
}
