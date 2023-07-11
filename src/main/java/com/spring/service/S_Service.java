package com.spring.service;

import java.util.List;

import com.spring.domain.S_DTO;
import com.spring.util.PageMaker;

public interface S_Service {
	List<S_DTO> getS(int shelter_no);
	List<S_DTO> getAllBoard();
	List<S_DTO> getAllBoardByPage(PageMaker pageMaker);
}
