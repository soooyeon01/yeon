package com.spring.service;

import java.util.List;

import com.spring.domain.P_DTO;
import com.spring.util.PageMaker;

public interface P_Service {
	List<P_DTO> getAllBoard();
	List<P_DTO> getAllBoardByPage(PageMaker pageMaker);
}
