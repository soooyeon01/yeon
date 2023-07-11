package com.spring.service;

import java.util.List;

import com.spring.domain.W_DTO;
import com.spring.util.PageMaker;

public interface W_Service {
	List<W_DTO> getW(int with_pet_no);
	List<W_DTO> getAllBoard();
	List<W_DTO> getAllBoardByPage(PageMaker pageMaker);
}
